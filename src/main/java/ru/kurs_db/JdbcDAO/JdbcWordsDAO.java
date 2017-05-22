package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.kurs_db.DAO.WordsDAO;
import ru.kurs_db.JdbcDAO.Models.Word;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by pacman29 on 20.05.17.
 */
public class JdbcWordsDAO extends JdbcInferiorDAO implements WordsDAO{
    public JdbcWordsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<Word> readWord = (rs, rowNum) ->
            new Word(rs.getInt("id"), rs.getString("word"),
                    rs.getString("slang"),rs.getString("dialect"),
                    rs.getInt("file_id"),rs.getString("discription"));

    @Override
    public Word create(@NotNull String word,
                       @NotNull String slang,
                       @NotNull String dialect,
                       @NotNull Integer file_id,
                       String discription) {
        String sql = "INSERT INTO words (word,dialect,slang,file_id,discription) VALUES(?,?,?,?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{word,dialect,slang,file_id,discription},readWord);
    }

    private boolean nullchecker(Object value,String sqlvalue, StringBuilder sql, List<Object> arr){
        if(value != null){
            sql.append(sqlvalue + " = ?,");
            arr.add(value);
        }

        return value != null;
    }

    @Override
    public Word change(Integer word_id, String new_word, String new_slang, String new_dialect, Integer new_file_id, String new_discription) {
        StringBuilder sql  = new StringBuilder("UPDATE words SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(new_word,"word",sql,tmp);
        nullchecker(new_slang,"slang",sql,tmp);
        nullchecker(new_dialect,"dialect",sql,tmp);
        nullchecker(new_file_id,"file_id",sql,tmp);
        nullchecker(new_discription,"discription",sql,tmp);
        sql.delete(sql.length()-1,sql.length());
        sql.append(" WHERE word_id = ? RETURNING *");
        tmp.add(word_id);

        return this.getJdbcTemplate().queryForObject(sql.toString(),tmp.toArray(),readWord);
    }

    @Override
    public Word delete(Integer id, String word) {
        String sql = "DELETE FROM words WHERE id = ? AND word = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id,word},readWord);
    }

    @Override
    public ArrayList<Word> words(Integer limit_s, Integer limit_f) {
        int size = limit_f - limit_f;
        String sql = "SELECT * FROM words LIMIT ? OFFSET ?";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{size,limit_s},(rs, rowNum) -> {
            ArrayList<Word> tmp = new ArrayList<>();
            while (rs.next()){
                tmp.add(new Word(rs.getInt("id"), rs.getString("word"),
                        rs.getString("slang"),rs.getString("dialect"),
                        rs.getInt("file_id"),rs.getString("discription")));
            }
            return tmp;
        });
    }
}
