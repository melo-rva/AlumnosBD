package model;

public class User {
    private String username;
    private String password; // DEMO: texto plano (no hacer en prod)


    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;

    }

    public String getUser() { return username; }
    public String getPassword() { return password; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

}
