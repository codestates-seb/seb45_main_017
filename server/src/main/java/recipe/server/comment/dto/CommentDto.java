package recipe.server.comment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


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
}
