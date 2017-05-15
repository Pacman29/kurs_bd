package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.Models.User;

/**
 * Created by pacman29 on 15.05.17.
 */
public interface RolesDAO {
    String getRole(@NotNull final String username);
    User changeRole(@NotNull final String username, @NotNull final String type);
    User addRole(@NotNull final String username, @NotNull final String type);
    User deleteRole(@NotNull final String username);
}
