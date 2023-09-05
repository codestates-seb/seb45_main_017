package recipe.server.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)  // 암호화되어 저장되기 때문에 길이 수정.
  private String password;

  private String username;

  @ElementCollection(fetch = FetchType.EAGER) // 사용자 등록 시 권한 테이블 생성.
  private List<String> roles = new ArrayList<>();






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
