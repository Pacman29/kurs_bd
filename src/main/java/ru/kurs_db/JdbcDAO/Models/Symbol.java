package ru.kurs_db.JdbcDAO.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 23.05.17.
 */
public class Symbol {
    private String symbol;
    private String dialect;
    private Integer file_id;
    private String description;

    public Symbol(@NotNull String symbol,@NotNull String dialect,@NotNull Integer file_id, String description) {
        this.symbol = symbol;
        this.dialect = dialect;
        this.file_id = file_id;
        this.description = description;
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

    public String getdescription() {
        return description;
    }
}
