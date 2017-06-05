package ru.kurs_db.Controllers.Responses;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.UserRole;

import java.util.List;

/**
 * Created by pacman29 on 17.05.17.
 */
public class SuccessUsersRolesResponse implements Response {
    public List<UserRole> getUsers() {
        return users;
    }

    final private List<UserRole> users;

    public SuccessUsersRolesResponse(@NotNull List<UserRole> users) {
        this.users = users;
    }

    @Override
    public String getMessage() {
        return "All users";
    }
}
