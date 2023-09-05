package recipe.server.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.server.exception.BusinessLogicException;
import recipe.server.exception.ExceptionCode;
import recipe.server.member.service.MemberService;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.repository.RecipesRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class RecipesService {

    private final RecipesRepository recipesRepository;

    private final MemberService memberService;

    /*
    public List<RecipesDto.recipesGetDto> getAllRecipes() {

        return recipesRepository.getAllRecipes();
    }

     */

    public Recipes createRecipes(Recipes recipes, long id) {

        recipes.setMember(memberService.getMember(id));

        return saveRecipes(recipes);
    }

   public void deleteRecipes (long id, long recipesId) {

        Recipes recipes = findVerifiedRecipesById(recipesId);
        verifyUserAuthorization(recipes.getMember().getId(), id);

        recipesRepository.delete(recipes);
   }

   public Recipes updateRecipes(Recipes recipes, long id) {

        Recipes foundRecipes = findRecipes(recipes.getRecipesId());

        verifyUserAuthorization(id, foundRecipes.getMember().getId());

        Optional.ofNullable(recipes.getRecipeTitle())
                .ifPresent(foundRecipes::setRecipeTitle);
        Optional.ofNullable(recipes.getRecipeBody())
                .ifPresent(foundRecipes::setRecipeBody);

        return saveRecipes(foundRecipes);
   }

    private Recipes saveRecipes(Recipes recipes) {
        return recipesRepository.save(recipes);
    }

    public Recipes findRecipes(long recipesId) {
        return findVerifiedRecipesById(recipesId);
    }

    public Recipes findVerifiedRecipesById(long recipesId) {

        Optional<Recipes> optionalRecipes = recipesRepository.findById(recipesId);
        Recipes foundRecipes = optionalRecipes.orElseThrow(() -> new BusinessLogicException(ExceptionCode.RECIPE_NOT_FOUND));

        return foundRecipes;
    }

    public void verifyUserAuthorization(long authorizeMemberId, long tryingMemberId) {

        if (authorizeMemberId != tryingMemberId)
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_MEMBER);
    }
}
