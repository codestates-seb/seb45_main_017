package recipe.server.recipes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import recipe.server.exception.BusinessLogicException;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipe;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.mapper.RecipesMapper;
import recipe.server.recipes.repository.RecipeRepository;
import recipe.server.recipes.repository.RecipesRepository;
import recipe.server.recipes.service.OpenApiManager;
import recipe.server.recipes.service.RecipesService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

import static retrofit2.Response.success;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipesService;
    private final RecipesMapper recipesMapper;
    private final RecipeRepository recipeRepository;
    private final OpenApiManager openApiManager;

    // TODO : 모든 레시피 get, delete
    // TODO : 모든 레시피 보여주기 -> 오픈 api 레시피 + post 레시피

    // 레시피 작성 (로그인 시)
    @PostMapping
    public ResponseEntity postRecipes(@Valid @RequestBody RecipesDto.recipesPostDto recipesPostDto){
                                     // @RequestParam("file") MultipartFile file) {
/*
        try {
            Recipes recipes = recipesService.createRecipes(
                    recipesMapper.recipesPostToRecipes(recipesPostDto),
                    recipesPostDto.getMemberId(),
                    file
            );

            RecipesDto.recipesResponseDto response = recipesMapper.recipesToRecipesResponse(recipes);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
 */

        try {
            // 예외가 발생할 수 있는 코드
            Recipes recipes = recipesService.createRecipes(
                    recipesMapper.recipesPostToRecipes(recipesPostDto),
                    recipesPostDto.getMemberId()
            );

            RecipesDto.recipesResponseDto response = recipesMapper.recipesToRecipesResponse(recipes);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // 예외 처리 로직
            e.printStackTrace(); // 또는 다른 로깅 방식을 사용
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 레시피 api 가져오기

    /*
    @GetMapping("open-api")
    public ResponseEntity<?> fetch() throws BusinessLogicException {

        return success(openApiManager.fetch().getBody());
        }
     */

    /*
    // 레시피 조회 -> 오픈 api 가져오기 (여러 레시피 보여주기)
    @GetMapping("/api/{api-key}/COOKRCP01/{start-num}/{end-num}")
    //api-key = e907859720c24072b3be, 요청 시작 위치 = start-num, 요청 종료 위치 = end-num
    public ResponseEntity getAllRecipes(@PathVariable("api-key") @Positive String apiKey,
                                        @PathVariable("start-num") @Positive int startNum,
                                        @PathVariable("end-num") @Positive int endNum) {

        return null;
    }
     */

    // 한 개의 레시피만 조회
    @GetMapping("/{recipe-id}")
    public ResponseEntity getRecipes(@PathVariable("recipe-id") @Positive long recipesId) {

        Recipes recipes = recipesService.findRecipe(recipesId);

        return new ResponseEntity<>(recipesMapper.recipesToRecipesResponse(recipes), HttpStatus.OK);
    }

    //TODO : pageable


    // 모든 레시피 조회
    @GetMapping
    public ResponseEntity findAllRecipes(@RequestParam @Positive int pageNumber,
                                         @RequestParam @Positive int pageSize) {

        Page<Recipes> recipes = recipesService.findAllRecipes(pageNumber, pageSize);
        RecipesDto.PageResponseDto pageResponseDto = recipesMapper.recipesPageToPageResponseDto(recipes);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }


    // 레시피 수정 (로그인 시 -> 자신이 작성한 레시피만)
    @PatchMapping("/{recipe-id}")
    public ResponseEntity patchRecipes(@PathVariable("recipe-id") @Positive Long recipesId,
                                       @Valid @RequestBody RecipesDto.recipesPatchDto recipesPatchDto) {

        recipesPatchDto.setRecipesId(recipesId);
        Recipes recipes = recipesService.updateRecipes(recipesMapper.recipesPatchToRecipes(recipesPatchDto),
                recipesPatchDto.getMemberId());

        return new ResponseEntity<>(recipesMapper.recipesToRecipesResponse(recipes), HttpStatus.OK);
    }


    // 레시피 삭제 (로그인 시 -> 자신이 작성한 레시피만)
    @DeleteMapping(value = "/{recipe-id}")
    public ResponseEntity deleteRecipes(@PathVariable("recipe-id") @Positive long recipesId,
                                        @Positive @RequestParam Long memberId) {

        {
            recipesService.deleteRecipes(recipesId, memberId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // TODO : 레시피 검색 -> 필터
    // TODO : 레시피 추천

    // 더미 데이터 가져오기
    @GetMapping("/data")
    public ResponseEntity getAllRecipes() {

        List<Recipe> recipes = recipeRepository.findAll();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
}
