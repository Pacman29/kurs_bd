package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.LanguageDAO;
import ru.kurs_db.JdbcDAO.Models.Language;

import java.util.ArrayList;

/**
 * Created by pacman29 on 30.05.17.
 */
@Service
public class JdbcLanguageDAO extends JdbcInferiorDAO implements LanguageDAO {
    public JdbcLanguageDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<Language> readLanguage = (rs, rowNum) ->
            new Language(rs.getString("language"));

    @Override
    public Language create(@NotNull String name) {
        String sql = "INSERT INTO languages (language) VALUES(?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{name},readLanguage);
    }

    @Override
    public Language update(@NotNull String oldname, @NotNull String newname) {
        String sql = "UPDATE languges SET language = ? WHERE language = ? RETURNING *";
        return  this.getJdbcTemplate().queryForObject(sql,new Object[]{newname,oldname},readLanguage);
    }

    @Override
    public Language delete(@NotNull String name) {
        String sql = "DELETE FROM languages WHERE language = ? RETURNING *";
        return  this.getJdbcTemplate().queryForObject(sql,new Object[]{name},readLanguage);
    }

    @Override
    public Language get(@NotNull String name) {
        String sql = "SELECT * FROM languages WHERE language = ?";
        return  this.getJdbcTemplate().queryForObject(sql,new Object[]{name},readLanguage);
    }

    @Override
    public ArrayList<Language> get_all() {
        String sql = "SELECT * FROM languages";
        return  this.getJdbcTemplate().queryForObject(sql,new Object[]{},(rs, rowNum) -> {
            ArrayList<Language> tmp= new ArrayList<>();
            while (rs.next()){
                tmp.add(readLanguage.mapRow(rs,rowNum));
            }
            return tmp;
        });
    }
}
