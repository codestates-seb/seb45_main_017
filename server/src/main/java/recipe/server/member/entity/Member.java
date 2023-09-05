package recipe.server.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(nullable = false, name = "LAST_MODIFIED_AT")
  private LocalDateTime modifiedAt = LocalDateTime.now();


//    // Recipe와의 OneToMany 관계 설정
//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    private List<Recipe> recipes;
//
//    // Comment와의 OneToMany 관계 설정
//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    private List<Comment> comments;
//
//    // MyPage와의 OneToOne 관계 설정
//    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
//    private MyPage myPage;
}
