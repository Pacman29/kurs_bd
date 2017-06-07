package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pacman29 on 04.06.17.
 */
public class CreateLanguageView{
    private String name;
    private String discription;

    public CreateLanguageView(@JsonProperty("language") String name,
                              @JsonProperty("discription") String discription) {
        this.name = name;
        this.discription = discription;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }
}
