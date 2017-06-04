package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pacman29 on 04.06.17.
 */
public class DeleteDialectView {
    private final String dialect;

    public DeleteDialectView(@JsonProperty("dialect") String dialect) {
        this.dialect = dialect;
    }

    public String getDialect() {
        return dialect;
    }
}
