package ru.kurs_db.Responses;

import org.jetbrains.annotations.NotNull;

/**
 * Created by lieroz on 7.05.17.
 */
public class ErrorResponse implements Response {
    private final String message;

    public ErrorResponse(@NotNull final String message) {
        this.message = message;
    }

    public final String getMessage() {
        return this.message;
    }
}
