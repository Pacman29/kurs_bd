package ru.kurs_db.Views;

import ru.kurs_db.Models.User;

/**
 * Created by lieroz on 7.05.17.
 */
public class UserPublicView {
    private final String username;
    private final String email;

    public UserPublicView(final User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }
}
