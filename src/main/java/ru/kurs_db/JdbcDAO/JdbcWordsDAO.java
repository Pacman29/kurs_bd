package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.WordsDAO;
import ru.kurs_db.JdbcDAO.Models.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman29 on 20.05.17.
 */
@Service
public class JdbcWordsDAO extends JdbcInferiorDAO implements WordsDAO {
    public JdbcWordsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<Word> readWord = (rs, rowNum) ->
            new Word(rs.getInt("id"), rs.getString("word"),
                    rs.getString("slang"), rs.getString("dialect"),
                    rs.getInt("file_id"), rs.getString("discription"));

    @Override
    public Word create(@NotNull String word, @NotNull String slang, @NotNull String dialect,
                       @NotNull Integer file_id, String discription) {
        String sql = "INSERT INTO words (word,dialect,slang,file_id,discription) VALUES(?,?,?,?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{word, dialect, slang, file_id, discription}, readWord);
    }

    @Override
    public Word change(@NotNull Integer word_id, String new_slang, String new_dialect, String new_discription) {
        StringBuilder sql = new StringBuilder("UPDATE getAllWords SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(new_slang, "slang", sql, tmp);
        nullchecker(new_dialect, "dialect", sql, tmp);
        nullchecker(new_discription, "discription", sql, tmp);
        sql.delete(sql.length() - 1, sql.length());
        sql.append(" WHERE word_id = ? RETURNING *");
        tmp.add(word_id);
        return this.getJdbcTemplate().queryForObject(sql.toString(), tmp.toArray(), readWord);
    }

    @Override
    public Word delete(@NotNull Integer id) {
        String sql = "DELETE FROM words WHERE id = ?  RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, readWord);
    }

    @Override
    public List<Word> getAllWords() {
        String sql = "SELECT * FROM words";
        return this.getJdbcTemplate().query(sql, readWord);
    }

    @Override
    public Word get(@NotNull Integer id) {
        String sql = "SELECT * FROM words WHERE id = ?";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, readWord);
    }

    @Override
    public List<Word> getword(@NotNull String word) {
        String sql = "SELECT * FROM words WHERE word = ?";
        return this.getJdbcTemplate().query(sql, new Object[]{word}, readWord);
    }

    @Override
    public List<Word> getword(@NotNull String word, String slang, String dialect) {
        StringBuilder sql = new StringBuilder("SELECT * FROM words WHERE word = ? ");
        ArrayList<Object> tmp = new ArrayList<>();
        tmp.add(word);
        if (slang != null) {
            sql.append("AND slang = ?");
            tmp.add(slang);
        }
        if (dialect != null) {
            sql.append(" AND dialect = ?");
            tmp.add(dialect);
        }
        return this.getJdbcTemplate().query(sql.toString(), tmp.toArray(), readWord);
    }

    @Override
    public List<Word> search(String word, String dialect, String slang) {
        StringBuilder sql = new StringBuilder("Select * FROM words ");
        if (word != null || dialect != null || slang != null) {
            sql.append(" WHERE ");
        }
        ArrayList<Object> tmp = new ArrayList<>();
        if (word != null) {
            sql.append(" word = ? AND");
            tmp.add(word);
        }
        if (dialect != null) {
            sql.append(" dialect = ? AND");
            tmp.add(dialect);
        }
        if (slang != null) {
            sql.append(" slang = ? AND");
            tmp.add(slang);
        }
        sql.delete(sql.length() - 3, sql.length());
        return this.getJdbcTemplate().query(sql.toString(), tmp.toArray(), readWord);
    }
}
