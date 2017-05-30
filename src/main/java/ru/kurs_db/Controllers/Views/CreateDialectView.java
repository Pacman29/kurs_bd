package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by pacman29 on 30.05.17.
 */
public class CreateDialectView {
    private String dialect;
    private String language;
    private String discription;

    public CreateDialectView(@JsonProperty("dialect") @NotNull String dialect,
                             @JsonProperty("language") @NotNull String language,
                             @JsonProperty("discription") @Nullable String discription) {
        this.dialect = dialect;
        this.language = language;
        this.discription = discription;
    }

    public String getDialect() {
        return dialect;
    }

    public String getLanguage() {
        return language;
    }

    public String getDiscription() {
        return discription;
    }
}
