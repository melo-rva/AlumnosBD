package model;

public class User {
    private final String username;
    private final String password; // DEMO: texto plano (no hacer en prod)
    private final String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean matches(String u, String p) {
        return username.equals(u) && password.equals(p);
    }
}
