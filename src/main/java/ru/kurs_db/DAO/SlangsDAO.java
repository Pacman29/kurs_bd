package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Slang;

import java.util.ArrayList;

/**
 * Created by pacman29 on 23.05.17.
 */
public interface SlangsDAO {
    Slang create (@NotNull final String slang);
    Slang delete (@NotNull final String slang);
    Slang update (@NotNull final String slang, @NotNull final String slang_new);
    ArrayList<Slang> getAllSlangs(final Integer limit_s, final Integer limit_f);
}
