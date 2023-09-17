package recipe.server.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recipe.server.comment.entity.Comment;
import recipe.server.myPage.entity.MyPageEntity;
import recipe.server.recipes.entity.Images;
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

  @Column(nullable = false)
  private String email;

  @Column(length = 100, nullable = false)  // 암호화되어 저장되기 때문에 길이 수정.
  private String password;

  @Column(nullable = false, unique = true)
  private String username;

  @ElementCollection(fetch = FetchType.EAGER) // 사용자 등록 시 권한 테이블 생성.
  private List<String> roles = new ArrayList<>();

  @Column(nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(nullable = false, name = "LAST_MODIFIED_AT")
  private LocalDateTime modifiedAt = LocalDateTime.now();

//  @Column(name = "oauth_type", columnDefinition = "VARCHAR(50)")
  private String oauthType;

   //TODO : 맞는지 확인 부탁드려요.

  // 관계설정 수정

    // Recipe와의 OneToMany 관계 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Recipes> recipes = new ArrayList<>();

    // Comment와의 OneToMany 관계 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    // MyPage와의 OneToOne 관계 설정
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MyPageEntity myPage;
}

