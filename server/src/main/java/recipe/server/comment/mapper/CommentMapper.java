package recipe.server.comment.mapper;

import org.mapstruct.Mapper;
import recipe.server.comment.dto.CommentDto;
import recipe.server.comment.entity.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentPostDtoToComment(CommentDto.CommentPostDto commentPostDto);

    Comment commentPatchDtoToComment(CommentDto.CommentPatchDto commentPatchDto);

    CommentDto.ResponseDto commentToResponseDto(Comment comment);


//    List<CommentDto.ResponseDto> commentsToResponseDtos(List<Comment> comments);

}
