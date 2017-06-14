package ru.kurs_db.JdbcDAO.Models;

import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 12.06.17.
 */
public class SymbolWithURL {
    private String symbol;
    private String dialect;
    private Integer file_id;
    private String description;
    private String file_name;
    private String url;

    public SymbolWithURL(@NotNull String symbol, @NotNull String dialect, @NotNull Integer file_id, String description, String file_name) {
        this.symbol = symbol;
        this.dialect = dialect;
        this.file_id = file_id;
        this.description = description;
        this.file_name = file_name;
        this.url = "";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getFile_name() {
        return file_name;
    }
}
