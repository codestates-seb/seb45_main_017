
package recipe.server.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),

    RECIPE_NOT_FOUND(404, "Recipe not found"),

    MEMBER_NOT_AUTHENTICATED(401, "Member not Authenticated"),

    UNAUTHORIZED_MEMBER(404, "Unauthorized member");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}

