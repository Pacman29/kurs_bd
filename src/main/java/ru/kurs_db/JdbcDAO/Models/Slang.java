package ru.kurs_db.JdbcDAO.Models;

/**
 * Created by pacman29 on 23.05.17.
 */
public class Slang {
    final private String slang;
    final private String description;

    public Slang(String slang, String description) {
        this.slang = slang;
        this.description = description;
    }

    public String getSlang() {
        return slang;
    }

    public String getdescription() {
        return description;
    }
}
