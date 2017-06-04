package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pacman29 on 04.06.17.
 */
public class DeleteWordView {
    private final Integer id;

    public DeleteWordView(@JsonProperty("id") Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
