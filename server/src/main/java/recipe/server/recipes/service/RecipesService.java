package recipe.server.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe.server.exception.BusinessLogicException;
import recipe.server.exception.ExceptionCode;
import recipe.server.member.service.MemberService;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.repository.RecipesRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class RecipesService {

    private final RecipesRepository recipesRepository;

    private final MemberService memberService;

    public Recipes createRecipes(Recipes recipes, long id) {

        //recipes.setMember(memberService.findMember(id));

        return saveRecipes(recipes);
    }

   public void deleteRecipes (long id, long recipesId) {

        Recipes recipes = findVerifiedRecipesById(recipesId);
        verifyUserAuthorization(recipes.getMember().getId(), id);

        recipesRepository.delete(recipes);
   }

    private Recipes saveRecipes(Recipes recipes) {
        return recipesRepository.save(recipes);
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
