package ru.kurs_db.JdbcDAO.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 30.05.17.
 */
public class Language {
    private final String language;
    private final String discription;

    public Language(@NotNull  String language, String discription) {
        this.language = language;
        this.discription = discription;
    }

    public String getLanguage() {
        return language;
    }

    public String getDiscription() {
        return discription;
    }
}
