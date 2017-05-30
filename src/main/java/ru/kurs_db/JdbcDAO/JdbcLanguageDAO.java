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
            new Language(rs.getString("language"),rs.getString("discription"));

    @Override
    public Language create(@NotNull String name, String discription) {
        String sql = "INSERT INTO languages (language,discription) VALUES(?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{name,discription},readLanguage);
    }

    @Override
    public Language update(@NotNull String oldname, String newname, String discription) {
        StringBuilder sql = new StringBuilder("UPDATE languges SET ");
        ArrayList<Object> args = new ArrayList<>();
        this.nullchecker(newname,"language",sql,args);
        this.nullchecker(discription,"discription",sql,args);
        sql.delete(sql.length()-1,sql.length());
        sql.append("WHERE language = ? RETURNING *");
        args.add(oldname);
        return  this.getJdbcTemplate().queryForObject(sql.toString(),args.toArray(),readLanguage);
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
