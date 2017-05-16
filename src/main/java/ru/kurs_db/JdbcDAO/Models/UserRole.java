package ru.kurs_db.JdbcDAO.Models;

/**
 * Created by pacman29 on 16.05.17.
 */
public class UserRole {
    public static enum role_type {ADMIN, USUAL, MODERATOR};
    private final String username;
    private final role_type type;

    public UserRole(String username, String type) {
        this.username = username;
        this.type = (type == null) ? (role_type.USUAL) : (role_type.valueOf(type));
    }

    public UserRole(String username, role_type type) {
        this.username = username;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public role_type getType() {
        return type;
    }
}
