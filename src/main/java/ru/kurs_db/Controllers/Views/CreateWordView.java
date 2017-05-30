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
    private MultipartFile file;
    private String discription;

    public CreateWordView(@JsonProperty("word") @NotNull final String word,
                          @JsonProperty("slang") @NotNull final String slang,
                          @JsonProperty("dialect") @NotNull final String dialect,
                          @JsonProperty("file") @NotNull final MultipartFile file,
                          @JsonProperty("discription") final String discription) {
        this.word = word;
        this.slang = slang;
        this.dialect = dialect;
        this.file = file;
        this.discription = discription;
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

    public MultipartFile getFile() {
        return file;
    }

    public String getDiscription() {
        return discription;
    }
}
