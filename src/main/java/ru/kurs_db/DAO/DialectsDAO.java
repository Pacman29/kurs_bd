package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kurs_db.JdbcDAO.Models.Dialect;

import java.util.ArrayList;

/**
 * Created by pacman29 on 23.05.17.
 */
public interface DialectsDAO {
    Dialect create (@NotNull final String dialect, @NotNull final String language, @Nullable String discription);
    Dialect delete (@NotNull final String dialect);
    Dialect update (@NotNull final String dialect,
                    final String dialect_new,
                    final String language_new,
                    final String discription);
    ArrayList<Dialect> getAllDialects(final Integer limit_s, final Integer limit_f);
}
