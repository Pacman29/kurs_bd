package ru.kurs_db.JdbcDAO.Models;

import org.jetbrains.annotations.Nullable;

/**
 * Created by pacman29 on 23.05.17.
 */
public class Dialect {
    private final String dialect;
    private final String language;
    private final String discription;

    public Dialect(String dialect, String language, String discription) {
        this.dialect = dialect;
        this.language = language;
        this.discription = discription;
    }

    public String getDialect() {
        return dialect;
    }

    public String getLanguage() {
        return language;
    }

    public String getDiscription() {
        return discription;
    }
}
