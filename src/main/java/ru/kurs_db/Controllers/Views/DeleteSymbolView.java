package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pacman29 on 04.06.17.
 */
public class DeleteSymbolView {
    private String symbol;
    private String dialect;

    public DeleteSymbolView(@JsonProperty("symbol") String symbol,
                            @JsonProperty("dialect") String dialect) {
        this.symbol = symbol;
        this.dialect = dialect;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDialect() {
        return dialect;
    }
}
