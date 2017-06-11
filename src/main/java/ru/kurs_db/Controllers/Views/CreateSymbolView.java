package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by pacman29 on 30.05.17.
 */
public class CreateSymbolView {
    private final String symbol;
    private final String dialect;
    private final String description;


    public CreateSymbolView(@JsonProperty("symbol") @NotNull String symbol,
                            @JsonProperty("dialect") @NotNull String dialect,
                            @JsonProperty("description") @NotNull String description) {
        this.symbol = symbol;
        this.dialect = dialect;
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getdescription() {
        return description;
    }

    public String getDialect() {
        return dialect;
    }
}
