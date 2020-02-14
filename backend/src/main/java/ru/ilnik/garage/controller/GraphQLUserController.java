package ru.ilnik.garage.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.model.enums.Role;
import ru.ilnik.garage.service.UserService;

@Slf4j
@Component
public class GraphQLUserController implements GraphQLMutationResolver {

    private final UserService userService;

    @Autowired
    public GraphQLUserController(UserService userService) {
        this.userService = userService;
    }

    public String register(final String userName, final String password, final Role role) {
        User user = new User(userName, password, role);
        log.info("Register new User: {}", user);
        userService.create(user);
        return "redirect:/";
    }

}
