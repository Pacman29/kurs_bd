package ru.kurs_db.JdbcDAO.Models;

/**
 * Created by pacman29 on 23.05.17.
 */
public class Dialect {
    private final String dialect;
    private final String language;

    public Dialect(String dialect, String language) {
        this.dialect = dialect;
        this.language = language;
    }

    public String getDialect() {
        return dialect;
    }

    public String getLanguage() {
        return language;
    }
}
