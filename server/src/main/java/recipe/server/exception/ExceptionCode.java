package recipe.server.exception;

import lombok.Getter;

public enum ExceptionCode {

    UNAUTHORIZED_MEMBER(404, "unauthorized member"),
    RECIPE_NOT_FOUND(404, "Recipe not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
