package com.example.securityaop.service;

import com.example.securityaop.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put("admin@example.com", new User("admin@example.com", "adminpass", Set.of("ADMIN")));
        users.put("user@example.com", new User("user@example.com", "userpass", Set.of("USER")));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }

    public boolean checkPassword(String email, String password) {
        User u = users.get(email);
        return u != null && u.getPassword().equals(password);
    }

    public Set<String> getRoles(String email) {
        User u = users.get(email);
        return u == null ? Set.of() : u.getRoles();
    }
}
