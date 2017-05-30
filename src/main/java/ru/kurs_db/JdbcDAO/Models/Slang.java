package ru.kurs_db.JdbcDAO.Models;

/**
 * Created by pacman29 on 23.05.17.
 */
public class Slang {
    final private String slang;
    final private String discription;

    public Slang(String slang, String discription) {
        this.slang = slang;
        this.discription = discription;
    }

    public String getSlang() {
        return slang;
    }

    public String getDiscription() {
        return discription;
    }
}
