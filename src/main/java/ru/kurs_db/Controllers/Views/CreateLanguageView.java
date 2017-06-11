package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pacman29 on 04.06.17.
 */
public class CreateLanguageView{
    private String name;
    private String description;

    public CreateLanguageView(@JsonProperty("language") String name,
                              @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getdescription() {
        return description;
    }
}
