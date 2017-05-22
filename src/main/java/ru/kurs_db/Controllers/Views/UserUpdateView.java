package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;

/**
 * Created by lieroz on 7.05.17.
 */
public class UserUpdateView {
    private final String username;
    private final String email;
    private final String password;

    public UserUpdateView(@JsonProperty("username") @Nullable final String username,
                          @JsonProperty("email") @Nullable final String email,
                          @JsonProperty("password") @Nullable final String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getPassword() {
        return this.password;
    }
}
