package ru.ilnik.garage.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_CLIENT,
    ROLE_MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
