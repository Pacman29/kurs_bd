package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Symbol;
import ru.kurs_db.JdbcDAO.Models.SymbolWithURL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman29 on 23.05.17.
 */
public interface SymbolsDAO {
    Symbol create(@NotNull String symbol, String dialect, Integer file_id, String description);
    Symbol change(@NotNull String symbol, @NotNull String dialect, String symbol_new, String dialect_new, String description);
    Symbol delete(@NotNull String symbol,@NotNull String dialect);
    List<SymbolWithURL> getAllSymbols();
    SymbolWithURL get(char symbol,String dialect);
    List<SymbolWithURL> convertToSymbol(String word, String dialect);
}
