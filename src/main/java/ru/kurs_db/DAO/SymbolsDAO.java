package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Symbol;

import java.util.ArrayList;

/**
 * Created by pacman29 on 23.05.17.
 */
public interface SymbolsDAO {
    Symbol create(@NotNull String symbol, @NotNull String dialect, @NotNull Integer file_id, String discription);
    Symbol update(@NotNull String symbol,@NotNull String dialect,String symbol_new,String dialect_new,Integer file_id_new,String discription);
    Symbol delete(@NotNull String symbol,@NotNull String dialect);
    ArrayList<Symbol> getAllSymbols(@NotNull final Integer limit_s,@NotNull final Integer limit_f);
    Symbol get(@NotNull String symbol,String dialect);
}
