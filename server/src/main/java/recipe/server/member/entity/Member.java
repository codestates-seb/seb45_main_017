package recipe.server.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recipe.server.comment.entity.Comment;
import recipe.server.myPage.entity.MypageEntity;
import recipe.server.recipes.entity.Recipes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;


  @Column(length = 100, nullable = false)  // 암호화되어 저장되기 때문에 길이 수정.
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String username;


  @ElementCollection(fetch = FetchType.EAGER) // 사용자 등록 시 권한 테이블 생성.
  private List<String> roles = new ArrayList<>();

  @Column(nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(nullable = false, name = "LAST_MODIFIED_AT")
  private LocalDateTime modifiedAt = LocalDateTime.now();



   //TODO : 맞는지 확인 부탁드려요.

    // Recipe와의 OneToMany 관계 설정
    @OneToMany(mappedBy = "recipesId", cascade = CascadeType.ALL)
    private List<Recipes> recipes;

    // Comment와의 OneToMany 관계 설정
    @OneToMany(mappedBy = "commentId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    // MyPage와의 OneToOne 관계 설정
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MypageEntity myPage;
}
