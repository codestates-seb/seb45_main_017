package recipe.server.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipes;

import java.util.List;

public interface RecipesRepository extends JpaRepository<Recipes, Long> {

    //List<RecipesDto.recipesGetDto> getAllRecipes();
}
