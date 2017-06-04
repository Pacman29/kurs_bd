package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Language;

import java.util.ArrayList;

/**
 * Created by pacman29 on 24.05.17.
 */
public interface LanguageDAO {
    Language create(@NotNull String name, String discription);
    Language change(@NotNull String oldname, String discription);
    Language delete(@NotNull String name);
    Language get(@NotNull String name);
    ArrayList<Language> get_all();
}

