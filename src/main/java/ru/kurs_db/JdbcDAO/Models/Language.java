package ru.kurs_db.JdbcDAO.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 30.05.17.
 */
public class Language {
    private final String language;
    private final String description;

    public Language(@NotNull  String language, String description) {
        this.language = language;
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public String getdescription() {
        return description;
    }
}
