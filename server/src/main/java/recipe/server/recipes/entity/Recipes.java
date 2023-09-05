package recipe.server.recipes.entity;

import lombok.*;
import recipe.server.comment.entity.Comment;
import recipe.server.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Recipes {
//TODO : 레시피 4가지 구성으로 고치기

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long recipesId;

    // 레시피 제목
    @Column(nullable = false)
    private String recipeTitle;

    private String recipeType;

    private String nutrition;

    // 레시피 내용
    // TODO : 이미지 삽입
    @Column(nullable = false)
    private String recipeBody;

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime modifiedAt;

    @ManyToOne (targetEntity = Member.class,  fetch = FetchType.LAZY)
    @JoinColumn (name = "id")
    private Member member;

    @OneToMany (targetEntity = Comment.class, mappedBy = "commentId")
    private List<Comment> comments = new ArrayList<>();

    public long getRecipesId() {

        return recipesId;
    }

    public void setRecipesId(long recipesId) {

        this.recipesId = recipesId;
    }

    public String getRecipeTitle() {

        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {

        this.recipeTitle = recipeTitle;
    }

    public String getRecipeBody() {

        return recipeBody;
    }

    public void setRecipeBody(String recipeBody) {

        this.recipeBody = recipeBody;
    }
    public Member getMember() {

        return member;
    }

    public void setMember(Member member) {

        this.member = member;
    }

    //api 가져오기
}
