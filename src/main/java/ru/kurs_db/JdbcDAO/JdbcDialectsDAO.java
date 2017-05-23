package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.DialectsDAO;
import ru.kurs_db.JdbcDAO.Models.Dialect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman29 on 23.05.17.
 */
@Service
public class JdbcDialectsDAO extends JdbcInferiorDAO implements DialectsDAO {
    public JdbcDialectsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<Dialect> readDialect = (rs, rowNum) ->
            new Dialect(rs.getString("dialect"),
                    rs.getString("language"));

    @Override
    public Dialect create(@NotNull String dialect, @NotNull String language) {
        String sql = "INSERT INTO dialects VALUES(dialect,language) VALUES (?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{dialect,language},readDialect);
    }

    @Override
    public Dialect delete(@NotNull String dialect) {
        String sql = "DELETE FROM dialects WHERE dialect = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{dialect},readDialect);
    }

    @Override
    public Dialect update(@NotNull String dialect, @NotNull String dialect_new, @NotNull String language_new) {
        StringBuilder sql = new StringBuilder("UPDATE dialect SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(dialect_new,"dialect",sql,tmp);
        nullchecker(language_new,"language",sql,tmp);
        sql.delete(sql.length()-1,sql.length());
        sql.append(" WHERE dialect = ? RETURNING *");
        tmp.add(dialect);
        return this.getJdbcTemplate().queryForObject(sql.toString(),tmp.toArray(),readDialect);
    }

    @Override
    public ArrayList<Dialect> getAllDialects(@NotNull final Integer limit_s,@NotNull final Integer limit_f) {
        String sql = "SELECT * FROM dialects LIMIT ? OFFSET ?";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{limit_s,limit_f}, ((rs, rowNum) -> {
            ArrayList<Dialect> tmp = new ArrayList<>();
            while (rs.next()){
                tmp.add(new Dialect(rs.getString("dialect"),rs.getString("language")));
            }
            return tmp;
        }));
    }
}
