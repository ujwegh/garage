package ru.ilnik.garage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ilnik.garage.model.User;
import ru.ilnik.garage.model.enums.Role;
import ru.ilnik.garage.repository.UserRepository;
import ru.ilnik.garage.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DbMigration {

    private final UserService service;
    private final UserRepository repository;

    private static final List<User> usersToSave;
    static {
        usersToSave = List.of(
          new User("admin@gmail.com", "password", Role.ROLE_ADMIN),
          new User("user@gmail.com", "password", Role.ROLE_USER),
          new User("manager@gmail.com", "password", Role.ROLE_MANAGER)
        );
    }

    @Autowired
    public DbMigration(UserService service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostConstruct
    void init(){
        if(repository.count() == 0) {
            usersToSave.forEach(service::create);
        }
    }
}
