package ru.kurs_db.Controllers.Responses;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.UserRole;
import ru.kurs_db.Responses.Response;

/**
 * Created by pacman29 on 17.05.17.
 */
public class SuccessUsersRolesResponse implements Response {
    public UserRole[] getUsers() {
        return users;
    }

    final private UserRole[] users;

    public SuccessUsersRolesResponse(@NotNull UserRole[] users) {
        this.users = users;
    }

    @Override
    public String getMessage() {
        return "All users";
    }
}
