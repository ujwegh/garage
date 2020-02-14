package ru.ilnik.garage.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.ilnik.garage.model.enums.Gender;
import ru.ilnik.garage.model.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends BaseEntity {

    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phone;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private String imageUuid;

    private boolean enabled = true;

    private LocalDateTime lastLoginDate;

    private String oauthClientId;

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>(Collections.singletonList(role));
    }
}
