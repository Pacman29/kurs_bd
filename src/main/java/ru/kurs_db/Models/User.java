package ru.kurs_db.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by lieroz on 6.05.17.
 */
public class User {
    private final Integer id;
    private final String username;
    private final String email;
    private final String hashedPassord;

    public User(@NotNull final Integer id,
                @NotNull final String username,
                @NotNull final String email,
                @NotNull final String hashedPassord) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hashedPassord = hashedPassord;
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
        return this.hashedPassord;
    }
}
