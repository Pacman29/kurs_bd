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
            new Slang(rs.getString("slang"), rs.getString("description"));

    @Override
    public Slang create(@NotNull String slang, String description) {
        String sql = "INSERT INTO slangs (slang,description) VALUES (?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{slang, description}, readSlang);
    }

    @Override
    public Slang delete(@NotNull String slang) {
        String sql = "DELETE FROM slangs WHERE slang = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{slang}, readSlang);
    }

    @Override
    public Slang change(@NotNull String slang, String slang_new, String description) {
        StringBuilder sql = new StringBuilder("UPDATE slangs SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(slang_new, "slang", sql, tmp);
        nullchecker(description, "description", sql, tmp);
        sql.delete(sql.length() - 1, sql.length());
        sql.append(" WHERE slang = ? RETURNING *");
        tmp.add(slang);
        return this.getJdbcTemplate().queryForObject(sql.toString(), tmp.toArray(), readSlang);
    }

    @Override
    public Slang get(@NotNull String slang) {
        String sql = "SELECT * FROM slangs WHERE slang = ?";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{slang}, readSlang);
    }

    @Override
    public List<Slang> getAllSlangs() {
        String sql = "SELECT * FROM slangs";
        return this.getJdbcTemplate().query(sql, new Object[]{}, readSlang);
    }
}
