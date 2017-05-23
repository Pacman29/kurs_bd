package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Dialect;

import java.util.ArrayList;

/**
 * Created by pacman29 on 23.05.17.
 */
public interface DialectsDAO {
    Dialect create (@NotNull final String dialect, @NotNull final String language);
    Dialect delete (@NotNull final String dialect);
    Dialect update (@NotNull final String dialect, @NotNull final String dialect_new, @NotNull final String language_new);
    ArrayList<Dialect> getAllDialects(final Integer limit_s, final Integer limit_f);
}
