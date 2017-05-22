package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by lieroz on 7.05.17.
 */
public class UserRegisterView {
    private final String username;
    private final String email;
    private final String password;

    public UserRegisterView(@JsonProperty("username") @NotNull final String username,
                            @JsonProperty("email") @NotNull final String email,
                            @JsonProperty("password") @NotNull final String password) {
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