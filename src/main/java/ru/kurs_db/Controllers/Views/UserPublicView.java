package ru.kurs_db.Controllers.Views;

import ru.kurs_db.JdbcDAO.Models.User;

import javax.servlet.http.HttpSession;

/**
 * Created by lieroz on 7.05.17.
 */
public class UserPublicView {
    private final String username;
    private final String email;
    private final Boolean isAdmin;
    private final Boolean isModerator;

    public UserPublicView(final User user, Boolean isAdmin, Boolean isModerator) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
    }

    public UserPublicView(HttpSession httpSession){
        this.username = (String) httpSession.getAttribute("username");
        this.email = (String) httpSession.getAttribute("email");
        this.isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
        this.isModerator = (Boolean) httpSession.getAttribute("isModerator");
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public Boolean getModerator() {
        return isModerator;
    }
}
