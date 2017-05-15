package ru.kurs_db.Responses;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kurs_db.Views.UserPublicView;

/**
 * Created by lieroz on 7.05.17.
 */
public class SuccessUserResponse implements Response {
    private final Integer userId;
    private final String message;
    private final UserPublicView view;

    public SuccessUserResponse(@NotNull final Integer userId,
                               @NotNull final String message,
                               @Nullable final UserPublicView view) {
        this.userId = userId;
        this.message = message;
        this.view = view;
    }

    public final Integer getUserId() {
        return this.userId;
    }

    public final String getMessage() {
        return this.message;
    }

    public final UserPublicView getView() {
        return this.view;
    }
}
