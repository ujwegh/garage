package ru.ilnik.garage.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.service.UserService;

import java.util.List;

@Slf4j
@Component
public class GraphQLUserQueries implements GraphQLQueryResolver {

    private final UserService userService;

    @Autowired
    public GraphQLUserQueries(UserService userService) {
        this.userService = userService;
    }

    @Secured("ROLE_ADMIN")
    public List<User> users() {
        log.info("Get all users");
        return userService.getAll();
    }
}
