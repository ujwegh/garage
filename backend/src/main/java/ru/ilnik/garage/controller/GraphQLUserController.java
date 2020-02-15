package ru.ilnik.garage.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.model.enums.Role;
import ru.ilnik.garage.service.UserService;
import ru.ilnik.garage.util.UserValidator;

import java.util.List;

@Slf4j
@Component
public class GraphQLUserController implements GraphQLMutationResolver {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserValidator userValidator;

    @Autowired
    public GraphQLUserController(UserService userService, AuthenticationManager authenticationManager, UserValidator userValidator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userValidator = userValidator;
    }



    @Secured("ROLE_ADMIN")
    public List<User> users() {
        log.info("Get all users");
        return userService.getAll();
    }

    @Secured({"ROLE_ADMIN, ROLE_USER", "ROLE_CLIENT", "ROLE_MANAGER"})
    public void update(User user) {
        log.info("Update user: {}", user);
        userService.update(user);
    }

    @Secured("ROLE_ADMIN")
    public void enableUser(Long id, Boolean enable) {
        userService.enable(id, enable);
    }

}
