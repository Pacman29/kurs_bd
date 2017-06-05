package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman29 on 23.05.17.
 */
public interface SymbolsDAO {
    Symbol create(@NotNull String symbol, String dialect, Integer file_id, String discription);
    Symbol change(@NotNull String symbol, @NotNull String dialect, String symbol_new, String dialect_new, String discription);
    Symbol delete(@NotNull String symbol,@NotNull String dialect);
    List<Symbol> getAllSymbols();
    Symbol get(char symbol,String dialect);
    List<Symbol> convertToSymbol(String word, String dialect);
}
