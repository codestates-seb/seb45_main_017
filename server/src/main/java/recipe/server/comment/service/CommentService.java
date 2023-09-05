package recipe.server.comment.service;

import org.springframework.stereotype.Service;
import recipe.server.comment.entity.Comment;
import recipe.server.comment.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment (Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment (Comment comment){
        Comment findComment = findVerifiedComment(comment.getCommentId());

        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> findComment.setContent(content));

        findComment.setModifiedAt(LocalDateTime.now()); // 수정시간

        return findComment;
    }

    // 조회 코드 임시
    public Comment findComment(Long commentId) {
        return findVerifiedComment(commentId);
    }

    // 조회 코드 임시
    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public Comment deleteComment(Long commentId) {
        Comment findComment = findVerifiedComment(commentId);

        commentRepository.deleteById(findComment.getCommentId());

        return findComment;
    }

    private Comment findVerifiedComment(Long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);

        return findComment.get();
    }

}
