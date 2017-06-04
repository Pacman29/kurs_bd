package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 30.05.17.
 */
public class ChangeDialectView {
    private String dialect;
    private String new_dialect;
    private String new_language;
    private String new_discription;

    public ChangeDialectView(@JsonProperty("dialect") @NotNull String dialect,
                             @JsonProperty("new_dialect") String new_dialect,
                             @JsonProperty("language") String new_language,
                             @JsonProperty("discription") String new_discription) {
        this.dialect = dialect;
        this.new_language = new_language;
        this.new_discription = new_discription;
        this.new_dialect = new_dialect;
    }

    public String getNew_dialect() {
        return new_dialect;
    }

    public String getDialect() {
        return dialect;
    }

    public String getNew_language() {
        return new_language;
    }

    public String getNew_discription() {
        return new_discription;
    }
}
