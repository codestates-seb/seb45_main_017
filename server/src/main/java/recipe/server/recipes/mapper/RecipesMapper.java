package recipe.server.recipes.mapper;

import org.springframework.stereotype.Component;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipes;

@Component
public class RecipesMapper {

    public Recipes recipesPostToRecipes (RecipesDto.recipesPostDto recipesPostDto) {

        if ( recipesPostDto == null){
            return null;
        }

        Recipes recipes = new Recipes();

        recipes.setRecipeTitle(recipesPostDto.getRecipeTitle());
        recipes.setRecipeBody(recipesPostDto.getRecipeBody());

        return recipes;
    }

    public RecipesDto.recipesResponseDto recipesToRecipesResponse(Recipes recipes) {

        if(recipes == null) {
            return null;
        }

        RecipesDto recipesDto = new RecipesDto();
        RecipesDto.recipesResponseDto recipesResponseDto = recipesDto.new recipesResponseDto();

        recipesResponseDto.setRecipesId(recipes.getRecipesId());
        recipesResponseDto.setId(recipes.getMember().getId());
        recipesResponseDto.setRecipeTitle(recipes.getRecipeTitle());
        recipesResponseDto.setRecipeBody(recipes.getRecipeBody());
        //recipesResponseDto.setCreateAt(recipes.getCreateAt());
        //recipesResponseDto.setModifiedAt(recipes.getModifiedAt());

        return recipesResponseDto;
    }
}
