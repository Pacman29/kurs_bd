package ru.kurs_db.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by lieroz on 7.05.17.
 */
public class UserLoginView {
    private final String username;
    private final String password;

    public UserLoginView(@JsonProperty("username") @NotNull final String username,
                         @JsonProperty("password") @NotNull final String password) {
        this.username = username;
        this.password = password;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getPassword() {
        return this.password;
    }
}
