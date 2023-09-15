package recipe.server.recipes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import recipe.server.exception.BusinessLogicException;
import recipe.server.member.entity.Member;
import recipe.server.member.service.MemberService;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipe;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.mapper.RecipesMapper;
import recipe.server.recipes.repository.ImageRepository;
import recipe.server.recipes.repository.RecipeRepository;
import recipe.server.recipes.repository.RecipesRepository;
import recipe.server.recipes.service.ImageService;
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
    private final MemberService memberService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;



    // TODO : 모든 레시피 get, delete
    // TODO : 모든 레시피 보여주기 -> 오픈 api 레시피 + post 레시피

    // 레시피 작성 (로그인 시)

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity postRecipes(@Valid @ModelAttribute RecipesDto.recipesPostDto postDto) {


        String imagePath = null; // 기본적으로 imagePath를 null로 초기화

        if (postDto.getImage() != null && !postDto.getImage().isEmpty()) {
            // 이미지 파일이 업로드되었을 때만 이미지를 업로드하고 경로를 설정
            imagePath = imageService.uploadAndSaveImage(postDto.getImage());
        }

        Recipes recipes = recipesMapper.recipesPostToRecipes(postDto);

        recipes.setImagePath(imagePath);

        Recipes created = recipesService.createRecipes(recipes);

        RecipesDto.recipesResponseDto responseDto = recipesMapper.recipesToRecipesResponse(created);


        return new ResponseEntity(responseDto, HttpStatus.CREATED);


    }


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
