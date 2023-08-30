package recipe.server.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.server.comment.dto.CommentDto;
import recipe.server.comment.entity.Comment;
import recipe.server.comment.mapper.CommentMapper;
import recipe.server.comment.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/Comment")
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }


//    @PostMapping
//    public ResponseEntity postComment(@Valid @RequestBody CommentDto.CommentPostDto commentPostDto,
//                                      @PathVariable("recipes-id") Long recipesId) {
//
//
//    }

    @PatchMapping("/{commentId}")
    public ResponseEntity patchComment(@PathVariable("commentId") @Positive Long commentId,
                                       @RequestBody CommentDto.CommentPatchDto commentPatchDto) {

        Comment comment = commentService.updateComment(commentMapper.commentPatchDtoToComment(commentPatchDto));

        return new ResponseEntity(commentMapper.commentToResponseDto(comment), HttpStatus.OK);
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") @Positive Long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity(HttpStatus.OK);
    }


}

