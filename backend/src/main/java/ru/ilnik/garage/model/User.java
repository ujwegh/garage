package ru.ilnik.garage.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.ilnik.garage.model.enums.AuthProvider;
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

    private String imageUrl;

    private boolean enabled = true;

    private LocalDateTime lastLoginDate;

    private String oauthClientId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>(Collections.singletonList(role));
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                ", imageUuid='" + imageUrl + '\'' +
                ", enabled=" + enabled +
                ", lastLoginDate=" + lastLoginDate +
                ", oauthClientId='" + oauthClientId + '\'' +
                '}';
    }
}
