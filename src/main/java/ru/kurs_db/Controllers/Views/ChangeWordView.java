package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pacman29 on 30.05.17.
 */
public class ChangeWordView {
    private final Integer word_id;
    private final String new_slang;
    private final String new_dialect;
    private final String new_description;

    public ChangeWordView(@JsonProperty("word_id") Integer word_id,
                          @JsonProperty("new_slang") String new_slang,
                          @JsonProperty("new_dialect") String new_dialect,
                          @JsonProperty("new_description") String new_description) {
        this.word_id = word_id;
        this.new_slang = new_slang;
        this.new_dialect = new_dialect;
        this.new_description = new_description;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public String getNew_slang() {
        return new_slang;
    }

    public String getNew_dialect() {
        return new_dialect;
    }

    public String getNew_description() {
        return new_description;
    }
}
