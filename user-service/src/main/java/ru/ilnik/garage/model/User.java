package ru.ilnik.garage.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.ilnik.garage.model.oauth.AuthProvider;
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
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(name = "oauth_client_id")
    private String oauthClientId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private AuthProvider provider = AuthProvider.LOCAL;

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
