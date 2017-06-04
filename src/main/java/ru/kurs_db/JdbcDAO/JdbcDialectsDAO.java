package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
                    rs.getString("language"),rs.getString("discription"));

    @Override
    public Dialect create(@NotNull String dialect, @NotNull String language, @Nullable String discription) {
        String sql = "INSERT INTO dialects (dialect,language,discription) VALUES (?,?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{dialect,language,discription},readDialect);
    }

    @Override
    public Dialect delete(@NotNull String dialect) {
        String sql = "DELETE FROM dialects WHERE dialect = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{dialect},readDialect);
    }

    @Override
    public Dialect change(@NotNull String dialect,
                          final String dialect_new,
                          final String language_new,
                          final String discription) {
        StringBuilder sql = new StringBuilder("UPDATE dialects SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(dialect_new,"dialect",sql,tmp);
        nullchecker(language_new,"language",sql,tmp);
        nullchecker(discription,"discription",sql,tmp);
        sql.delete(sql.length()-1,sql.length());
        sql.append(" WHERE dialect = ? RETURNING *");
        tmp.add(dialect);
        return this.getJdbcTemplate().queryForObject(sql.toString(),tmp.toArray(),readDialect);
    }

    @Override
    public ArrayList<Dialect> getAllDialects() {
        String sql = "SELECT * FROM dialects";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{}, ((rs, rowNum) -> {
            ArrayList<Dialect> tmp = new ArrayList<>();
            do{
                tmp.add(readDialect.mapRow(rs,rowNum));
            }while (rs.next());
            return tmp;
        }));
    }
}
