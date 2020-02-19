package ru.ilnik.garage.util;

import org.springframework.security.core.GrantedAuthority;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.model.enums.Role;
import ru.ilnik.garage.security.UserPrincipal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EntityMapper {
    private EntityMapper() {
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>(user.getRoles());
        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), authorities);

    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }
}
