package ru.ilnik.garage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilnik.garage.controller.dto.AuthResponse;
import ru.ilnik.garage.controller.dto.UserLoginDto;
import ru.ilnik.garage.controller.dto.UserRegistrationDto;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.model.enums.AuthProvider;
import ru.ilnik.garage.security.TokenProvider;
import ru.ilnik.garage.service.UserService;
import ru.ilnik.garage.util.exception.BadRequestException;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder,
                          TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        if (userService.isExist(registrationDto.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        user.setProvider(AuthProvider.LOCAL);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userService.create(user);
//        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully@"));
        return ResponseEntity.ok().build();
    }

}
