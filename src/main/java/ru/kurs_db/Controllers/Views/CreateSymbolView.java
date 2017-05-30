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
    private final String discription;
    private MultipartFile file;


    public CreateSymbolView(@JsonProperty("symbol") @NotNull String symbol,
                            @JsonProperty("dialect") @NotNull String dialect,
                            @JsonProperty("discription") @NotNull String discription,
                            @JsonProperty("file") @NotNull MultipartFile file) {
        this.symbol = symbol;
        this.dialect = dialect;
        this.discription = discription;
        this.file = file;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDiscription() {
        return discription;
    }

    public String getDialect() {
        return dialect;
    }

    public MultipartFile getFile() {
        return file;
    }
}
