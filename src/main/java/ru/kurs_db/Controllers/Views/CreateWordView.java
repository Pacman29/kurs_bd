package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by pacman29 on 20.05.17.
 */
public class CreateWordView {
    private String word;
    private String slang;
    private String dialect;
    private String description;

    public CreateWordView(@JsonProperty("word") @NotNull final String word,
                          @JsonProperty("slang") @NotNull final String slang,
                          @JsonProperty("dialect") @NotNull final String dialect,
                          @JsonProperty("description") final String description) {
        this.word = word;
        this.slang = slang;
        this.dialect = dialect;
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public String getSlang() {
        return slang;
    }

    public String getDialect() {
        return dialect;
    }

    public String getdescription() {
        return description;
    }
}
