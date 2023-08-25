package recipe.server.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String password;

  private String username;


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
