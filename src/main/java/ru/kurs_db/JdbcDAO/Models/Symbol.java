package ru.kurs_db.JdbcDAO.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 23.05.17.
 */
public class Symbol {
    private String symbol;
    private String dialect;
    private Integer file_id;

    public Symbol(@NotNull String symbol,@NotNull String dialect,@NotNull Integer file_id) {
        this.symbol = symbol;
        this.dialect = dialect;
        this.file_id = file_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDialect() {
        return dialect;
    }

    public Integer getFile_id() {
        return file_id;
    }
}
