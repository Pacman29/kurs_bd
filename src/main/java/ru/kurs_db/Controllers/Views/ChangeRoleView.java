package ru.kurs_db.Controllers.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Created by pacman29 on 14.05.17.
 */
public class ChangeRoleView {
    private final String username;
    private final String newrole;

    public ChangeRoleView(@JsonProperty("username") @NotNull final String username,
                          @JsonProperty("newrole") @NotNull final String newrole){
        this.username = username;
        this.newrole = newrole;
    }

    public final String getUsername() {
        return username;
    }

    public final String getNewrole() {
        return newrole;
    }
}
