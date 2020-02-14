package ru.ilnik.garage.controller.dto;

import lombok.Getter;
import lombok.Setter;
import ru.ilnik.garage.model.enums.Role;

import java.io.Serializable;

@Getter
@Setter
public class UserRegistrationDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private Role role;
}
