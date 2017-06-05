package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.UserRole;

import java.util.List;

/**
 * Created by pacman29 on 15.05.17.
 */
public interface RolesDAO {
    UserRole.role_type getRole(@NotNull final String username);
    UserRole changeRole(@NotNull final String username, @NotNull UserRole.role_type type);
    UserRole addRole(@NotNull final String username, @NotNull UserRole.role_type type);
    UserRole deleteRole(@NotNull final String username);
    List<UserRole> getAllUsersRoles(@NotNull String username);
}
