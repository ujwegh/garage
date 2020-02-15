package ru.ilnik.garage.service;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import ru.ilnik.garage.model.User;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CustomOidcUserService extends OidcUserService {
    private final UserService userService;

    @Autowired
    public CustomOidcUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();
        System.out.println(oidcUser);

        User user;
        if (!userService.isExist(oidcUser.getEmail())){
            user = new User();
            user.setOauthClientId((String) attributes.get("sub"));
            user.setPassword(generateRandomPass());
            user.setEmail(oidcUser.getEmail());
            user.setFirstName(oidcUser.getGivenName());
            user.setLastName(oidcUser.getFamilyName());
            user.setLastLoginDate(LocalDateTime.now());
            user.setPhone(oidcUser.getPhoneNumber());
            userService.update(user);
        }
        return oidcUser;
    }

    private String generateRandomPass() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
                .build();
        return pwdGenerator.generate(16);
    }
}
