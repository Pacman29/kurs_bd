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
            new Slang(rs.getString("slang"), rs.getString("discription"));

    @Override
    public Slang create(@NotNull String slang, String discription) {
        String sql = "INSERT INTO slangs (slang,discription) VALUES (?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{slang, discription}, readSlang);
    }

    @Override
    public Slang delete(@NotNull String slang) {
        String sql = "DELETE FROM slangs WHERE slang = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{slang}, readSlang);
    }

    @Override
    public Slang change(@NotNull String slang, String slang_new, String discription) {
        StringBuilder sql = new StringBuilder("UPDATE slangs SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(slang_new, "slang", sql, tmp);
        nullchecker(discription, "discription", sql, tmp);
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
