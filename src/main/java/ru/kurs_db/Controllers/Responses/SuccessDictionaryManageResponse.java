package ru.kurs_db.Controllers.Responses;

import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 30.05.17.
 */
public class SuccessDictionaryManageResponse implements Response{
    private final String message;
    private final String username;

    public SuccessDictionaryManageResponse(@NotNull String username) {
        this.username = username;
        this.message = "Request from the user: "+username+" was successful";
    }


    @Override
    public String getMessage() {
        return this.message;
    }

    public String getUsername() {
        return username;
    }
}
