package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pacman29 on 04.06.17.
 */
public class SearchView {
    private String word;
    private String dialect;
    private String slang;

    public SearchView(@JsonProperty("word") String word,
                      @JsonProperty("dialect") String dialect,
                      @JsonProperty("slang") String slang) {
        this.word = word;
        this.dialect = dialect;
        this.slang = slang;
    }

    public String getWord() {
        return word;
    }

    public String getDialect() {
        return dialect;
    }

    public String getSlang() {
        return slang;
    }
}
