package ru.ilnik.garage.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import ru.ilnik.garage.controller.dto.UserRegistrationDto;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.service.UserService;
import ru.ilnik.garage.util.UserValidator;

@Slf4j
@Component
public class GraphQLUserMutations implements GraphQLMutationResolver {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserValidator userValidator;

    @Autowired
    public GraphQLUserMutations(UserService userService, AuthenticationManager authenticationManager, UserValidator userValidator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userValidator = userValidator;
    }

    public void register(UserRegistrationDto dto) {
        log.info("Register new user: {}", dto);



    }

    @Secured({"ROLE_ADMIN, ROLE_USER", "ROLE_CLIENT", "ROLE_MANAGER"})
    public void update(User user) {
        log.info("Update user: {}", user);
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
