package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 05.06.17.
 */
public class ConverterView {
    private String word;
    private String dialect;

    public ConverterView(@JsonProperty("word") @NotNull String word,@JsonProperty("dialect") @NotNull String dialect) {
        this.word = word;
        this.dialect = dialect;
    }

    public String getWord() {
        return word;
    }

    public String getDialect() {
        return dialect;
    }
}
