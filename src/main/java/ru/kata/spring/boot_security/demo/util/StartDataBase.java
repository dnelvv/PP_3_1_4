package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class StartDataBase {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public StartDataBase(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void startDB() {
        User user = new User("user", "user", "user", "user@mail.ru", "user");
        User admin = new User("admin", "admin", "admin", "admin@mail.ru", "admin");
        User superAdmin = new User("superadmin", "Tom", "White", "dtom@mail.ru", "superadmin");
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        roleService.addRole(userRole);
        roleService.addRole(adminRole);

        userService.addUser(user, new String[]{"ROLE_USER"});
        userService.addUser(admin, new String[]{"ROLE_ADMIN"});
        userService.addUser(superAdmin, new String[]{"ROLE_ADMIN", "ROLE_USER"});
    }
}