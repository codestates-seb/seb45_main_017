package recipe.server.recipes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 데이터 확인용 엔티티
// 삭제 예정
@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Recipe() {
        // 기본 생성자 내용
    }

    public Recipe(String recipeTitle, String recipeType, String nutrition, String recipeBody) {
        this.recipeTitle = recipeTitle;
        this.recipeType = recipeType;
        this.nutrition = nutrition;
        this.recipeBody = recipeBody;
    }
}
