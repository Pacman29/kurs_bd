package ru.kurs_db.Controllers.Errors;

/**
 * Created by pacman29 on 16.05.17.
 */
public class ErrorChangeException extends Exception{
    public ErrorChangeException() {
        super();
    }

    public ErrorChangeException(String message) {
        super(message);
    }
}
