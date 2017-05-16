package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.RolesDAO;
import ru.kurs_db.JdbcDAO.Models.UserRole;
import ru.kurs_db.Models.User;

/**
 * Created by pacman29 on 15.05.17.
 */
@Service
public class JdbcRolesDAO extends JdbcInferiorDAO implements RolesDAO{

    public JdbcRolesDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<UserRole> readUserRole = (rs, rowNum) -> {
        return new UserRole(rs.getString("username"),rs.getString("role"));
    };

    @Override
    public UserRole.role_type getRole(@NotNull String username) throws EmptyResultDataAccessException{
        final String sql = "SELECT type FROM roles WHERE username = ?";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{username},(rs,rowNum)->{
            return UserRole.role_type.valueOf(rs.getString("type"));
        });
    }

    @Override
    public UserRole changeRole(@NotNull String username, @NotNull UserRole.role_type type) throws DataAccessException{
        if(type == UserRole.role_type.USUAL){
            return this.deleteRole(username);
        }
        return this.getJdbcTemplate().queryForObject("SELECT change_or_insert_role(?,?)",
                new Object[]{username,type.name()},readUserRole);
    }

    @Override
    public UserRole addRole(@NotNull String username, @NotNull UserRole.role_type type) throws DataAccessException{
        if(type == UserRole.role_type.USUAL) return new UserRole(username, type);
        final String sql =  "INSERT INTO roles (role, username) VALUES (?,?)  RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{type.name(),username},readUserRole);
    }

    @Override
    public UserRole deleteRole(@NotNull String username) {
        final String sql =  "DELETE FROM roles WHERE username = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{username},(rs, rowNum) -> {
            return new UserRole(rs.getString("username"),UserRole.role_type.USUAL);
        });
    }

    @Override
    public UserRole[] getAllUsersRoles() {
        final String sql =  "SELECT users.username,roles.role FROM users LEFT OUTER JOIN roles ON users.username = role.username ";
        return this.getJdbcTemplate().queryForObject(sql,(rs, rowNum) -> {
            UserRole[] tmp = new UserRole[]{null};
            int i = 0;
            while (rs.next()){
                tmp[i++] = new UserRole(rs.getString("username"),rs.getString("role"));
            }
            return tmp;
        });
    }


}
