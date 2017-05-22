package ru.kurs_db.JdbcDAO.Models;

/**
 * Created by pacman29 on 20.05.17.
 */
public class Objfile {
    private String filename;
    private Integer Id;

    public Objfile(Integer id, String filename) {
        this.filename = filename;
        Id = id;
    }

    public String getFilename() {
        return filename;
    }

    public Integer getId() {
        return Id;
    }
}
