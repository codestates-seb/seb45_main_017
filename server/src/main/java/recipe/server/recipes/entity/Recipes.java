package recipe.server.recipes.entity;

import lombok.*;
import recipe.server.comment.entity.Comment;
import recipe.server.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Recipes {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long recipesId;

    // 레시피 제목
    @Column(nullable = false)
    private String recipeTitle;

    // 레시피 유형
    private String recipeType;

    // 레시피 영양소
    private String nutrition;

    // 레시피 내용
    @Column(nullable = false)
    private String recipeBody;

    // 사진 이름
    private String fileName;

    // 이미지 저장 경로
    private String filePath;

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

    public String getRecipeType() {

        return recipeType;
    }

    public void setRecipeType(String recipeType) {

        this.recipeType = recipeType;
    }

    public String getNutrition() {

        return nutrition;
    }

    public void setNutrition(String nutrition) {

        this.nutrition = nutrition;
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

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    public String getFilePath() {

        return filePath;
    }

    public void setFilePath(String filePath) {

        this.filePath = filePath;
    }

    public String getCreateAt() {
        return createAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getModifiedAt() {

        return modifiedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}