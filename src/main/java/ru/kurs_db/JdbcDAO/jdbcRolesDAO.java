package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.RolesDAO;
import ru.kurs_db.Models.User;

/**
 * Created by pacman29 on 15.05.17.
 */
@Service
public class jdbcRolesDAO extends JdbcInferiorDAO implements RolesDAO{

    public jdbcRolesDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public String getRole(@NotNull String username) {
        final String sql = "SELECT type FROM roles WHERE username = ?";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{username},(rs,rowNum)->{
            return rs.getString("type");
        });
    }

    @Override
    public User changeRole(@NotNull String username, @NotNull String type) {
        return null;
    }

    @Override
    public User addRole(@NotNull String username, @NotNull String type) {
        return null;
    }

    @Override
    public User deleteRole(@NotNull String username) {
        return null;
    }
}
