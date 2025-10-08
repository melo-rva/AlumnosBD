package model;

import java.util.*;

public class AuthService {

    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        // Usuarios fijos
        users.put("mariam", new User("mariam", "mariam123", "USER"));
        users.put("karen", new User("karen", "karen123", "USER"));
        users.put("alejandro", new User("alejandro", "alejandro123", "USER"));
        users.put("miguel", new User("miguel", "miguel123", "USER"));
        users.put("eder", new User("eder", "eder123", "USER"));

    }

    public Optional<User> login(String username, String password) {
        User u = users.get(username);
        if (u != null && u.matches(username, password)) {
            return Optional.of(u);
        }
        return Optional.empty();
    }
}
