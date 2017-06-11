package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Slang;

import java.util.List;

/**
 * Created by pacman29 on 23.05.17.
 */
public interface SlangsDAO {
    Slang create (@NotNull final String slang, final String description);
    Slang delete (@NotNull final String slang);
    Slang change (@NotNull final String slang, final String slang_new, final String description);
    Slang get(@NotNull String slang);
    List<Slang> getAllSlangs();
}
