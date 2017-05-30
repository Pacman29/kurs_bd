package ru.kurs_db.JdbcDAO.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 30.05.17.
 */
public class Language {
    private final String language;

    public Language(@NotNull String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
