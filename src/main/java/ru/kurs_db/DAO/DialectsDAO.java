package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kurs_db.JdbcDAO.Models.Dialect;

import java.util.List;

/**
 * Created by pacman29 on 23.05.17.
 */
public interface DialectsDAO {
    Dialect create (@NotNull final String dialect, @NotNull final String language, @Nullable String description);
    Dialect delete (@NotNull final String dialect);
    Dialect change(@NotNull final String dialect, final String dialect_new, final String language_new, final String description);
    List<Dialect> getAllDialects();
}
