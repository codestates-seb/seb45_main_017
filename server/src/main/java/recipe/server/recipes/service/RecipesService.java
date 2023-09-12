package recipe.server.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import recipe.server.exception.BusinessLogicException;
import recipe.server.exception.ExceptionCode;
import recipe.server.member.service.MemberService;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.repository.RecipesRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class RecipesService {

    private final RecipesRepository recipesRepository;

    private final MemberService memberService;


    /*
    public List<RecipesDto.recipesResponseDto> getAllRecipes() {

        return recipesRepository.findAll();
    }
     */




    public Page<Recipes> findAllRecipes(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber -1, pageSize, Sort.by("recipesId"));
        Page<Recipes> recipes = recipesRepository.findAll(pageable);

        return recipes;
    }

    public Recipes findRecipe(long recipesId) {

        return findVerifiedRecipesById(recipesId);
    }

    public Recipes createRecipes(Recipes recipes, long id, MultipartFile file) throws Exception {

        recipes.setMember(memberService.findMember(id));
        String projectPath = System.getProperty("user.dir")
                + "\\\\src\\\\main\\\\java\\\\recipe\\\\recipes\\\\entity\\\\Recipes";

        UUID uuid = UUID.randomUUID();

        String imageName = uuid + "_" + file.getOriginalFilename();

        File savaFile = new File(projectPath, imageName);

        file.transferTo(savaFile);

        recipes.setFileName(imageName);
        recipes.setFilePath("/recipes/" + imageName);

        recipesRepository.save(recipes);

        return saveRecipes(recipes);
    }

   public void deleteRecipes ( long id,long recipesId) {

        Recipes recipes = findVerifiedRecipesById(recipesId);
        verifyUserAuthorization(recipes.getMember().getMemberId(), id);

        recipesRepository.delete(recipes);
   }

   public Recipes updateRecipes(Recipes recipes, long id) {

        Recipes foundRecipes = findRecipes(recipes.getRecipesId());

        verifyUserAuthorization(id, foundRecipes.getMember().getMemberId());

        Optional.ofNullable(recipes.getRecipeTitle())
                .ifPresent(foundRecipes::setRecipeTitle);
        Optional.ofNullable(recipes.getRecipeType())
                        .ifPresent(foundRecipes::setRecipeType);
        Optional.ofNullable(recipes.getNutrition())
                        .ifPresent(foundRecipes::setNutrition);
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
