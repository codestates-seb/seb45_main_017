package recipe.server.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.server.recipes.entity.Recipe;
import recipe.server.recipes.entity.Recipes;

// 데이터 확인용 레포지토리
// 삭제 예정
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
