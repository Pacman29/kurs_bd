package ru.kurs_db.JdbcDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 * Created by lieroz on 6.05.17.
 */
@Component
public class JdbcInferiorDAO extends JdbcDaoSupport {
    @Autowired
    public JdbcInferiorDAO(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }
}
