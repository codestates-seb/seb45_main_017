package recipe.server.recipes.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipes;
import recipe.server.utils.PageInfo;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipesMapper {

    public Recipes recipesPostToRecipes (RecipesDto.recipesPostDto recipesPostDto) {

        if ( recipesPostDto == null){
            return null;
        }

        Recipes recipes = new Recipes();

        recipes.setRecipeTitle(recipesPostDto.getRecipeTitle());
        recipes.setRecipeType(recipesPostDto.getRecipeType());
        recipes.setNutrition(recipesPostDto.getNutrition());
        recipes.setRecipeBody(recipesPostDto.getRecipeBody());
        recipes.setFilePath(recipesPostDto.getFilePath());
        recipes.setFileName(recipesPostDto.getFileName());

        return recipes;
    }

    public Recipes recipesPatchToRecipes(RecipesDto.recipesPatchDto recipesPatchDto) {

        Recipes recipes = new Recipes();

        recipes.setRecipeTitle(recipesPatchDto.getRecipeTitle());
        recipes.setRecipeType(recipesPatchDto.getRecipeType());
        recipes.setNutrition(recipesPatchDto.getNutrition());
        recipes.setRecipeBody(recipesPatchDto.getRecipeBody());
        recipes.setRecipesId(recipesPatchDto.getRecipesId());
        recipes.setFilePath(recipesPatchDto.getFilePath());
        recipes.setFileName(recipesPatchDto.getFileName());

        return recipes;
    }

    public RecipesDto.recipesResponseDto recipesToRecipesResponse(Recipes recipes) {

        if(recipes == null) {
            return null;
        }

        RecipesDto recipesDto = new RecipesDto();
        RecipesDto.recipesResponseDto recipesResponseDto = recipesDto.new recipesResponseDto();

        recipesResponseDto.setRecipesId(recipes.getRecipesId());
        recipesResponseDto.setMemberId(recipes.getMember().getMemberId());
        recipesResponseDto.setRecipeTitle(recipes.getRecipeTitle());
        recipesResponseDto.setRecipeType(recipes.getRecipeType());
        recipesResponseDto.setNutrition(recipes.getNutrition());
        recipesResponseDto.setRecipeBody(recipes.getRecipeBody());
       // recipesResponseDto.setCreateAt(recipes.getCreateAt());
       // recipesResponseDto.setModifiedAt(recipes.getModifiedAt());

        return recipesResponseDto;
    }

    public RecipesDto.PageResponseDto recipesPageToPageResponseDto(Page<Recipes> recipes) {

        if(recipes == null){

            return null;
        }
        else {

            RecipesDto.PageResponseDto pageResponseDto = new RecipesDto.PageResponseDto();

            List<RecipesDto.recipesResponseDto> pageResponseDtos = recipes
                    .stream()
                    .map(recipe -> recipesToRecipesResponse(recipe))
                    .collect(Collectors.toList());

            pageResponseDto.setRecipes(pageResponseDtos);
            pageResponseDto.setPageInfo(PageInfo.builder()
                    .pageNumber(recipes.getNumber() + 1)
                    .pageSize(recipes.getSize())
                    .totalElements(recipes.getTotalElements())
                    .totalPages(recipes.getTotalPages())
                    .build());

            return pageResponseDto;
        }
    }
}
