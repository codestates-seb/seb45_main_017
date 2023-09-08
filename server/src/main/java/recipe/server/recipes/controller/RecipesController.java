package recipe.server.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.mapper.RecipesMapper;
import recipe.server.recipes.service.RecipesService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipesController {

    private final RecipesService recipesService;
    private final RecipesMapper recipesMapper;

    // TODO : 모든 레시피 get, delete
    // TODO : 모든 레시피 보여주기 -> 오픈 api 레시피 + post 레시피

    // 레시피 작성 (로그인 시)
    @PostMapping("/recipes")
    public ResponseEntity postRecipes(@Valid @RequestBody RecipesDto.recipesPostDto recipesPostDto) {

        Recipes recipes = recipesService.createRecipes(recipesMapper.recipesPostToRecipes(recipesPostDto), recipesPostDto.getId());

        return new ResponseEntity<>(recipesMapper.recipesToRecipesResponse(recipes), HttpStatus.CREATED);
    }

    // 레시피 조회 -> 오픈 api 가져오기 (여러 레시피 보여주기)
    @GetMapping("/api/{api-key}/COOKRCP01/{start-num}/{end-num}")
    //api-key = e907859720c24072b3be, 요청 시작 위치 = start-num, 요청 종료 위치 = end-num
    public ResponseEntity getAllRecipes(@PathVariable("api-key") @Positive String apiKey,
                                        @PathVariable("start-num") @Positive int startNum,
                                        @PathVariable("end-num") @Positive int endNum) {

        return null;
    }

    // 한 개의 레시피만 조회
    @GetMapping("recipes/{recipe-id}")
    public ResponseEntity getRecipes(@PathVariable("recipe-id") @Positive long recipesId) {

        Recipes recipes = recipesService.findRecipe(recipesId);

        return new ResponseEntity<>(recipesMapper.recipesToRecipesResponse(recipes), HttpStatus.OK);
    }

    /*
    // 모든 레시피 조회
    @GetMapping("/recipes")
    public ResponseEntity findAllRecipes(@RequestParam @Positive int pageNumber,
                                         @RequestParam @Positive int pageSize) {

        Page<Recipes> recipes = recipesService.findAllRecipes(pageNumber, pageSize);
        RecipesDto.PageResponseDto pageResponseDto = recipesMapper.recipesPageToPageResponseDto(recipes);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

     */

    // 레시피 수정 (로그인 시 -> 자신이 작성한 레시피만)
    @PatchMapping("recipes/{recipe-id}")
    public ResponseEntity patchRecipes(@PathVariable("recipe-id") @Positive long recipesId,
                                       @Valid @RequestBody RecipesDto.recipesPatchDto recipesPatchDto) {

        recipesPatchDto.setRecipesId(recipesId);
        Recipes recipes = recipesService.updateRecipes(recipesMapper.recipesPatchToRecipes(recipesPatchDto),
                recipesPatchDto.getId());

        return new ResponseEntity<>(recipesMapper.recipesToRecipesResponse(recipes), HttpStatus.OK);
    }


    // 레시피 삭제 (로그인 시 -> 자신이 작성한 레시피만)
    @DeleteMapping("recipes/{recipe-id}")
    public ResponseEntity deleteRecipes(@PathVariable("recipe-id") @Positive long recipesId,
                                        @Positive @RequestParam long id) {

        {
            recipesService.deleteRecipes(recipesId, id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // TODO : 레시피 검색 -> 필터
        // TODO : 레시피 추천
    }
}
