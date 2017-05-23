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
public class JdbcSymbolsDAO extends JdbcInferiorDAO implements SymbolsDAO{
    public JdbcSymbolsDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private final RowMapper<Symbol> readSymbol = (rs, rowNum) ->
            new Symbol(rs.getString("symbol"),
                    rs.getString("dialect"),rs.getInt("file_id"));

    @Override
    public Symbol create(@NotNull String symbol, @NotNull String dialect, @NotNull Integer file_id) {
        String sql = "INSERT INTO symbols VALUES(symbol,dialect,file_id) VALUES (?,?,?) RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{symbol,dialect,file_id},readSymbol);
    }

    @Override
    public Symbol update(@NotNull String symbol,@NotNull String dialect, @NotNull String symbol_new, @NotNull String dialect_new, @NotNull Integer file_id_new) {
        StringBuilder sql = new StringBuilder("UPDATE symbols SET ");
        List<Object> tmp = new ArrayList<>();
        nullchecker(symbol_new,"symbol",sql,tmp);
        nullchecker(dialect_new,"dialect",sql,tmp);
        nullchecker(file_id_new,"file_id",sql,tmp);
        sql.delete(sql.length()-1,sql.length());
        sql.append(" WHERE symbol = ? AND dialect = ?  RETURNING *");
        tmp.add(symbol);
        tmp.add(dialect);
        return this.getJdbcTemplate().queryForObject(sql.toString(),tmp.toArray(),readSymbol);
    }

    @Override
    public Symbol delete(@NotNull String symbol,@NotNull String dialect) {
        String sql = "DELETE FROM symbols WHERE symbol = ? AND dialect = ? RETURNING *";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{symbol,dialect},readSymbol);
    }

    @Override
    public ArrayList<Symbol> getAllSymbols(@NotNull final Integer limit_s,@NotNull final Integer limit_f) {
        String sql = "SELECT * FROM symbols LIMIT ? OFFSET ?";
        return this.getJdbcTemplate().queryForObject(sql, new Object[]{limit_s,limit_f}, ((rs, rowNum) -> {
            ArrayList<Symbol> tmp = new ArrayList<>();
            while (rs.next()){
                tmp.add(readSymbol.mapRow(rs,rowNum));
            }
            return tmp;
        }));
    }

    @Override
    public Symbol get(@NotNull String symbol,@NotNull String dialect) {
        String sql = "SELECT * FROM symbols WHERE symbol = ? AND dialect = ? ";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{symbol,dialect},readSymbol);
    }
}
