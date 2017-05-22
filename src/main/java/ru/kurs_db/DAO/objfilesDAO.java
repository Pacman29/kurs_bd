package ru.kurs_db.DAO;

import ru.kurs_db.JdbcDAO.Models.Objfile;

/**
 * Created by pacman29 on 20.05.17.
 */
public interface objfilesDAO {
    Objfile create(String filename);
    Objfile delete(Integer Id);
    Objfile change(Integer Id, String new_filename);
}
