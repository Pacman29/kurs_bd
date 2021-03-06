package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 04.06.17.
 */
public class ChangeSymbolView {
    private String symbol;
    private String dialect;
    private String symbol_new;
    private String dialect_new;
    private String description;

    public ChangeSymbolView(@JsonProperty("symbol") @NotNull String symbol,
                            @JsonProperty("dialect") @NotNull String dialect,
                            @JsonProperty("new_symbol") String symbol_new,
                            @JsonProperty("new_dialect") String dialect_new,
                            @JsonProperty("description") String description) {
        this.symbol = symbol;
        this.dialect = dialect;
        this.symbol_new = symbol_new;
        this.dialect_new = dialect_new;
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDialect() {
        return dialect;
    }

    public String getSymbol_new() {
        return symbol_new;
    }

    public String getDialect_new() {
        return dialect_new;
    }

    public String getdescription() {
        return description;
    }
}
