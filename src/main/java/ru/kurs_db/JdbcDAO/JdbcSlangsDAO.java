package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.SlangsDAO;
import ru.kurs_db.JdbcDAO.Models.Slang;

import java.util.ArrayList;

/**
 * Created by pacman29 on 23.05.17.
 */
@Service
public class JdbcSlangsDAO extends JdbcInferiorDAO implements SlangsDAO {
    public JdbcSlangsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Slang create(@NotNull String slang) {
        return null;
    }

    @Override
    public Slang delete(@NotNull String slang) {
        return null;
    }

    @Override
    public Slang update(@NotNull String slang, @NotNull String slang_new) {
        return null;
    }

    @Override
    public ArrayList<Slang> getAllSlangs(Integer limit_s, Integer limit_f) {
        return null;
    }
}
