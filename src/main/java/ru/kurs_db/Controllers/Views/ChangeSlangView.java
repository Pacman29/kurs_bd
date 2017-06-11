package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 04.06.17.
 */
public class ChangeSlangView {
    private String Slang;
    private String new_Slang;
    private String description;

    public ChangeSlangView(@JsonProperty("slang") @NotNull String slang,
                           @JsonProperty("new_slang") String new_Slang,
                           @JsonProperty("new_description") String description) {
        Slang = slang;
        this.new_Slang = new_Slang;
        this.description = description;
    }

    public String getSlang() {
        return Slang;
    }

    public String getNew_Slang() {
        return new_Slang;
    }

    public String getdescription() {
        return description;
    }
}
