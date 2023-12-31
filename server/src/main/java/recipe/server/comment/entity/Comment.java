package recipe.server.comment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime modifiedAt;
}
