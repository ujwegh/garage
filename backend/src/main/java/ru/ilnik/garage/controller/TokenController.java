package ru.ilnik.garage.controller;

import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final AuthenticationRequest user) {

//        return jwtGenerator.generate(user);
        return "jwtGenerator.generate(user);";

    }
}
