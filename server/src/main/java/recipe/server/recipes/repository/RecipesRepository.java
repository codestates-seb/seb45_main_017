package recipe.server.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe.server.recipes.entity.Recipes;

public interface RecipesRepository extends JpaRepository<Recipes, Long> {
}
