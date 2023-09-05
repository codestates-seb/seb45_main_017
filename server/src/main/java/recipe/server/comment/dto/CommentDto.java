package recipe.server.comment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class CommentDto {

    @Getter
    @Setter
    public static class CommentPostDto {

        private String content;

    }

    @Getter
    @Setter
    public static class CommentPatchDto {

        private Long commentId;

        private String content;
    }

    @Getter
    @Setter
    public static class ResponseDto {

        private Long commentId;

        private long recipesId;

        private String content;
    }
}
