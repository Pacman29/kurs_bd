package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 30.05.17.
 */
public class CreateSlangView {
    private String slang;
    private String description;

    public CreateSlangView(@JsonProperty("slang") @NotNull String slang,
                           @JsonProperty("description") String description) {
        this.slang = slang;
        this.description = description;
    }

    public String getSlang() {
        return slang;
    }

    public String getdescription() {
        return description;
    }
}
