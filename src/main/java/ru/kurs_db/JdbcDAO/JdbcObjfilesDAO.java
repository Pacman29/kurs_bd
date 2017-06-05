package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.ObjfilesDAO;
import ru.kurs_db.JdbcDAO.Models.Objfile;

/**
 * Created by pacman29 on 20.05.17.
 */
@Service
public class JdbcObjfilesDAO extends JdbcInferiorDAO implements ObjfilesDAO {
    public JdbcObjfilesDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<Objfile> readObjfile = (rs, rowNum) ->
            new Objfile(rs.getInt("id"), rs.getString("name"));

    @Override
    public Objfile create(@NotNull String filename) {
        String sql = "INSERT INTO objfiles (name) VALUES(?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{filename},readObjfile);
    }

    @Override
    public Objfile delete(@NotNull Integer Id) {
        String sql = "DELETE FROM objfiles WHERE id = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{Id},readObjfile);
    }

    @Override
    public Objfile change(@NotNull Integer Id,@NotNull String new_filename) {
        String sql = "UPDATE objfiles SET name = ? WHERE id = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{new_filename, Id},readObjfile);
    }
}
