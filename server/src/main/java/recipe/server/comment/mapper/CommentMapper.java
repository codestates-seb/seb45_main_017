package recipe.server.comment.mapper;

import recipe.server.comment.dto.CommentDto;
import recipe.server.comment.entity.Comment;

public interface CommentMapper {

    Comment commentPostDtoToComment(CommentDto.CommentPostDto commentPostDto);

    Comment commentPatchDtoToComment(CommentDto.CommentPatchDto commentPatchDto);


}
