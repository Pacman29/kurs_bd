package ru.kurs_db.Controllers.Errors;

/**
 * Created by pacman29 on 15.05.17.
 */
public class ErrorAccessException extends RuntimeException{
    public ErrorAccessException() {
        super();
    }

    public ErrorAccessException(String message) {
        super(message);
    }
}
