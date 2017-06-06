package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.UserDAO;
import ru.kurs_db.JdbcDAO.Models.User;
import org.jetbrains.annotations.NotNull;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lieroz on 6.05.17.
 */
@Configuration
@PropertySource("classpath:admin.properties")
@Service
public class JdbcUserDAO extends JdbcInferiorDAO implements UserDAO {
    public JdbcUserDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Value("${admin.username}")
    private String a_name;

    @Value("${admin.password}")
    private  String a_password;

    @Value("${admin.email}")
    private  String a_email;

    @PostConstruct
    private void initAdmin(){
        PasswordEncoder enc = new BCryptPasswordEncoder();
        this.getJdbcTemplate().queryForObject("SELECT create_admin(?,?,?)",
                new Object[]{this.a_name,this.a_email, enc.encode(this.a_password)},(rs, num)->{return null;});
    }

    private final RowMapper<User> readUser = (rs, rowNum) ->
            new User(rs.getInt("id"), rs.getString("username"),
                    rs.getString("email"), rs.getString("password"));

    @Override
    public final User insert(@NotNull final String username, @NotNull final String email, @NotNull final String password) {
        final String sql = "INSERT INTO users (username, email, password) VALUES(?, ?, ?) RETURNING *";
        return getJdbcTemplate().queryForObject(sql, new Object[]{username, email, password}, readUser);
    }

    @Override
    public final User update(@NotNull final Integer id, @Nullable final String username, @Nullable final String email, @Nullable final String password) {
        final StringBuilder sql = new StringBuilder("UPDATE users SET");
        final List<Object> args = new ArrayList<>();
        if (username != null) {
            sql.append(" username = ?,");
            args.add(username);
        }
        if (email != null) {
            sql.append(" email = ?,");
            args.add(email);
        }
        if (password != null) {
            sql.append(" password = ?,");
            args.add(password);
        }
        if (!args.isEmpty()) {
            sql.delete(sql.length() - 1, sql.length());
            sql.append(" WHERE id = ? RETURNING *");
            args.add(id);
            return getJdbcTemplate().queryForObject(sql.toString(), args.toArray(), readUser);
        }
        return null;
    }

    @Override
    public final User delete(@NotNull final Integer id) {
        final String sql = "DELETE FROM users WHERE id = ? RETURNING *";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, readUser);
    }

    @Override
    public final User findById(@NotNull final Integer id) {
        final String sql = "SELECT * FROM users WHERE id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, readUser);
    }

    @Override
    public final User findByUsername(@NotNull final String username) {
        final String sql = "SELECT * FROM users WHERE username = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{username}, readUser);
    }
}
