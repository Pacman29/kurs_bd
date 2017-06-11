package ru.kurs_db.JdbcDAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.kurs_db.DAO.SymbolsDAO;
import ru.kurs_db.JdbcDAO.Models.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman29 on 24.05.17.
 */
@Service
public class JdbcSymbolsDAO extends JdbcInferiorDAO implements SymbolsDAO {
    public JdbcSymbolsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<Symbol> readSymbol = (rs, rowNum) ->
            new Symbol(rs.getString("symbol"),
                    rs.getString("dialect"),
                    rs.getInt("file_id"),
                    rs.getString("description"));

    @Override
    public Symbol create(@NotNull String symbol, String dialect, Integer file_id, String description) {
        String sql = "INSERT INTO symbols (symbol,dialect,file_id,description) VALUES (?,?,?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{symbol, dialect, file_id, description}, readSymbol);
    }

    @Override
    public Symbol change(@NotNull String symbol, @NotNull String dialect, String symbol_new, String dialect_new, String description) {
        StringBuilder sql = new StringBuilder("UPDATE symbols SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(symbol_new, "symbol", sql, tmp);
        nullchecker(dialect_new, "dialect", sql, tmp);
        nullchecker(description, "description", sql, tmp);
        sql.delete(sql.length() - 1, sql.length());
        sql.append(" WHERE symbol = ? AND dialect = ?  RETURNING *");
        tmp.add(symbol);
        tmp.add(dialect);
        return this.getJdbcTemplate().queryForObject(sql.toString(), tmp.toArray(), readSymbol);
    }

    @Override
    public Symbol delete(@NotNull String symbol, @NotNull String dialect) {
        String sql = "DELETE FROM symbols WHERE symbol = ? AND dialect = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{symbol, dialect}, readSymbol);
    }

    @Override
    public List<Symbol> getAllSymbols() {
        String sql = "SELECT * FROM symbols";
        return this.getJdbcTemplate().query(sql, new Object[]{}, readSymbol);
    }

    @Override
    public Symbol get(char symbol, @NotNull String dialect) {
        String sql = "SELECT * FROM symbols WHERE symbol = ? AND dialect = ? ";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{symbol, dialect}, readSymbol);
    }

    @Override
    public List<Symbol> convertToSymbol(String word, String dialect) {
        char[] symbols = word.toCharArray();
        List<Symbol> result = new ArrayList<>();
        for (char symbol : symbols) {
            result.add(this.get(symbol, dialect));
        }
        return result;
    }
}
