package ru.kurs_db.JdbcDAO.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by lieroz on 6.05.17.
 */
public class User {
    private final Integer id;
    private final String username;
    private final String email;
    private final String hashedPassword;

    public User(@NotNull final Integer id,
                @NotNull final String username,
                @NotNull final String email,
                @NotNull final String hashedPassword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public User(@NotNull final String username,
                @NotNull final String email,
                @NotNull final String hashedPassword) {
        this.id = null;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public final Integer getId() {
        return this.id;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getHashedPassword() {
        return this.hashedPassword;
    }
}

