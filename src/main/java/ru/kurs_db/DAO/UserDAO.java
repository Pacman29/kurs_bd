package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kurs_db.Models.User;

/**
 * Created by lieroz on 6.05.17.
 */
public interface UserDAO {
    User insert(@NotNull final String username, @NotNull final String email, @NotNull final String password);
    User update(@NotNull final Integer id, @Nullable final String username, @Nullable final String email, @Nullable final String password);
    User delete(@NotNull final Integer id);
    User findById(@NotNull final Integer id);
    User findByUsername(@NotNull final String username);
}
