package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 30.05.17.
 */
public class CreateSlangView {
    private String slang;
    private String discription;

    public CreateSlangView(@JsonProperty("slang") @NotNull String slang,
                           @JsonProperty("discription") String discription) {
        this.slang = slang;
        this.discription = discription;
    }

    public String getSlang() {
        return slang;
    }

    public String getDiscription() {
        return discription;
    }
}
