package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pacman29 on 04.06.17.
 */
public class DeleteSlangView {
    private final String slang;

    public DeleteSlangView(@JsonProperty("slang") String slang) {
        this.slang = slang;
    }

    public String getSlang() {
        return slang;
    }
}
