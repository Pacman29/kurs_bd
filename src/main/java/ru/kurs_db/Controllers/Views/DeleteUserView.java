package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 17.05.17.
 */
public class DeleteUserView {
    private final String username;

    public DeleteUserView(@JsonProperty("username") @NotNull String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
