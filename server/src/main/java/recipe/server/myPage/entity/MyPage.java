package recipe.server.myPage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recipe.server.member.entity.Member;
import recipe.server.recipes.entity.Recipes;

import javax.persistence.*;


/*
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String saveHeartId;

    private boolean saveHeart = false;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(targetEntity = Recipes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipesId")
    private Recipes recipes;
}

 */
