package ru.kurs_db.JdbcDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lieroz on 6.05.17.
 */
@Component
public class JdbcInferiorDAO extends JdbcDaoSupport {
    @Autowired
    public JdbcInferiorDAO(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    protected final static Logger LOGGER = LoggerFactory.getLogger(JdbcInferiorDAO.class);

    protected boolean nullchecker(Object value, String sqlvalue, StringBuilder sql, List<Object> arr) {
        if (value != null) {
            sql.append(sqlvalue + " = ?,");
            arr.add(value);
        }

        return value != null;
    }
}
