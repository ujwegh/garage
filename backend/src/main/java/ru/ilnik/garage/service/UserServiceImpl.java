package ru.ilnik.garage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.repository.UserRepository;
import ru.ilnik.garage.util.EntityMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static ru.ilnik.garage.util.ValidationUtil.checkNotFound;
import static ru.ilnik.garage.util.ValidationUtil.checkNotFoundWithId;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final Sort SORT_EMAIL = Sort.by(Sort.Direction.ASC, "email");
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, @Lazy PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        checkNotFound(user, "user must not be null");
        return prepareAndSave(user);
    }

    @Override
    public void delete(long id) {
        log.debug("Delete user by id {}", id);
        repository.deleteById(id);
    }

    @Override
    public User get(long id) {
        log.debug("get user by id {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public User getByEmail(@NotNull String email) {
        log.debug("Get user by email {}", email);
        return checkNotFound(repository.findByEmail(email).orElse(null), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        log.debug("Get all users");
        return repository.findAll(SORT_EMAIL);
    }

    @Override
    public void update(@NotNull User user) {
        log.debug("update user by user {}", user);
        prepareAndSave(user);
    }

    @Override
    public void enable(long id, boolean enabled) {
        log.debug(enabled ? "enable {}" : "disable {}", id);
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    @Override
    public boolean isExist(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(@NotNull @NotBlank String email) throws UsernameNotFoundException {
        log.info("Load user by user email {}", email);
        User user = repository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " is not found"));
        return EntityMapper.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        log.info("Load user by user id {}", id);
        User user = repository.findById(id)
                .orElseThrow( () -> new UsernameNotFoundException("User with id" + id + " is not found"));
        return EntityMapper.create(user);
    }

    private User prepareAndSave(User user) {
        log.debug("prepare and save {}", user);
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return repository.save(user);
    }
}
