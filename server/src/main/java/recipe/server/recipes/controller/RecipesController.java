package recipe.server.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.mapper.RecipesMapper;
import recipe.server.recipes.service.RecipesService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipesService;
    private final RecipesMapper recipesMapper;

    // 레시피 작성 (로그인 시)
    @PostMapping
    public ResponseEntity postRecipes(@Valid @RequestBody RecipesDto.recipesPostDto recipesPostDto) {

        Recipes recipes = recipesService.createRecipes(recipesMapper.recipesPostToRecipes(recipesPostDto),
                recipesPostDto.getId());

        return new ResponseEntity<>(recipesMapper.recipesToRecipesResponse(recipes), HttpStatus.CREATED);
    }

    // 레시피 조회 -> 오픈 api 가져오기
    @GetMapping
    public ResponseEntity getRecipes() {

        return null;
    }

    // 레시피 삭제 (로그인 시 -> 자신이 작성한 레시피만)
    @DeleteMapping("/{recipesId}")
    public ResponseEntity deleteRecipes(@PathVariable("recipesId") @Positive long recipesId,
                                        @Positive @RequestParam long id) {

        recipesService.deleteRecipes(recipesId, id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 레시피 수정 (로그인 시 -> 자신이 작성한 레시피만)
    @PatchMapping
    public ResponseEntity patchRecipes() {

        return null;
    }

    // 레시피 검색
    // 레시피 찜 (로그인 시 / 마이페이지에서 확인 가능)
}
