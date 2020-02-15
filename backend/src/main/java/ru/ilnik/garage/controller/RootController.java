package ru.ilnik.garage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.model.enums.Role;

@Slf4j
@Controller
public class RootController {

    @GetMapping("/")
    public String root(Model model) {
        return "main";
    }

    @GetMapping("/auth/signup")
    public String login() {
        return "custom-login";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @Secured({"ROLE_CLIENT","ROLE_USER"})
    @GetMapping("/client")
    public String client(Model model) {
        return "client";
    }

    @Secured("ROLE_MANAGER")
    @GetMapping("/manager")
    public String manager(Model model) {
        return "manager";
    }
}
