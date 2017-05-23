package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.SlangsDAO;
import ru.kurs_db.JdbcDAO.Models.Slang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman29 on 23.05.17.
 */
@Service
public class JdbcSlangsDAO extends JdbcInferiorDAO implements SlangsDAO {
    public JdbcSlangsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<Slang> readSlang = (rs, rowNum) ->
            new Slang(rs.getString("slang"));

    @Override
    public Slang create(@NotNull String slang) {
        String sql = "INSERT INTO slangs VALUES(slang) VALUES (?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{slang},readSlang);
    }

    @Override
    public Slang delete(@NotNull String slang) {
        String sql = "DELETE FROM slangs WHERE slang = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{slang},readSlang);
    }

    @Override
    public Slang update(@NotNull String slang, @NotNull String slang_new) {
        StringBuilder sql = new StringBuilder("UPDATE slangs SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(slang_new,"slang",sql,tmp);
        sql.delete(sql.length()-1,sql.length());
        sql.append(" WHERE slang = ? RETURNING *");
        tmp.add(slang);
        return this.getJdbcTemplate().queryForObject(sql.toString(),tmp.toArray(),readSlang);
    }

    @Override
    public ArrayList<Slang> getAllSlangs(Integer limit_s, Integer limit_f) {
        String sql = "SELECT * FROM slangs LIMIT ? OFFSET ?";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{limit_s,limit_f}, ((rs, rowNum) -> {
            ArrayList<Slang> tmp = new ArrayList<>();
            while (rs.next()){
                tmp.add(readSlang.mapRow(rs,rowNum));
            }
            return tmp;
        }));
    }
}
