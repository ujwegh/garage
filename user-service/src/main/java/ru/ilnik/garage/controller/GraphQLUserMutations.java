package ru.ilnik.garage.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ilnik.garage.controller.dto.AuthResponse;
import ru.ilnik.garage.controller.dto.UserLoginDto;
import ru.ilnik.garage.controller.dto.UserRegistrationDto;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.model.oauth.AuthProvider;
import ru.ilnik.garage.security.TokenProvider;
import ru.ilnik.garage.security.UserPrincipal;
import ru.ilnik.garage.service.UserService;
import ru.ilnik.garage.util.exception.BadRequestException;
import ru.ilnik.garage.util.exception.NotFoundException;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.Collections;

import static ru.ilnik.garage.util.EntityMapper.toUserLoginDto;

@Slf4j
@Component
public class GraphQLUserMutations implements GraphQLMutationResolver {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public GraphQLUserMutations(UserService userService, AuthenticationManager authenticationManager,
                                TokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Secured("ROLE_ANONYMOUS")
    public AuthResponse signIn(@Valid UserLoginDto loginDto) {
        log.info("Login: {}", loginDto);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userService.get(userPrincipal.getId());
        user.setLastLoginDate(LocalDateTime.now());
        userService.update(user);
        return new AuthResponse(token);
    }

    @Secured("ROLE_ANONYMOUS")
    public AuthResponse signUp(@Valid UserRegistrationDto registrationDto) {
        log.info("Register new user: {}", registrationDto);
        if (userService.isExist(registrationDto.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        user.setProvider(AuthProvider.LOCAL);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(registrationDto.getRole()));
        userService.create(user);
        UserLoginDto loginDto = toUserLoginDto(registrationDto);
        return signIn(loginDto);
    }

    @Secured({"ROLE_ADMIN, ROLE_USER", "ROLE_CLIENT", "ROLE_MANAGER"})
    public void update(User user) {
        log.info("Update user: {}", user);
        if (user.getId() == null) throw new NotFoundException("User id must not be null.");
        userService.update(user);
    }

    @Secured("ROLE_ADMIN")
    public void enableUser(Long id, Boolean enable) {
        log.info(enable ? "enable user with id: {}" : "disable user with id: {}", id);
        userService.enable(id, enable);
    }

    @Secured("ROLE_ADMIN")
    public void delete(Long id) {
        log.info("Delete user with id: {}", id);
        userService.delete(id);
    }

}
