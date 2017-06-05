package ru.kurs_db.Controllers.Responses;

import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 16.05.17.
 */
public class SuccessChangeRoleResponse implements Response {
    private final String message;
    private final String username;
    private final String newtype;

    public SuccessChangeRoleResponse(@NotNull String username, @NotNull String newtype) {
        this.message = "User successfully changed the role";
        this.username = username;
        this.newtype = newtype;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getUsername() {
        return username;
    }

    public String getNewtype() {
        return newtype;
    }
}
