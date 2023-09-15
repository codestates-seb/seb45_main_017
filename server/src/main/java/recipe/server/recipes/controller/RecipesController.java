package recipe.server.recipes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import recipe.server.comment.dto.CommentDto;
import recipe.server.exception.BusinessLogicException;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipe;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.mapper.RecipesMapper;
import recipe.server.recipes.repository.RecipeRepository;
import recipe.server.recipes.repository.RecipesRepository;
import recipe.server.recipes.service.RecipesService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import static retrofit2.Response.success;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipesService;
    private final RecipesMapper recipesMapper;
    private final RecipeRepository recipeRepository;

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

    // 레시피 조회 -> 오픈 api 가져오기 (여러 레시피 보여주기)
    //https://openapi.foodsafetykorea.go.kr/api/e907859720c24072b3be/COOKRCP01/json/1/100

    @GetMapping("/open-api")
    public ResponseEntity<List<RecipesDto.ApiMainDto>> fetch() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://openapi.foodsafetykorea.go.kr/api/e907859720c24072b3be/COOKRCP01/json/1/100";

        // API에서 JSON 데이터 가져오기
        String jsonString = restTemplate.getForObject(url, String.class);

        JSONParser jsonParser = new JSONParser();
        List<RecipesDto.ApiMainDto> result = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
            JSONObject cookRcp01 = (JSONObject) jsonObject.get("COOKRCP01");
            JSONArray rowArray = (JSONArray) cookRcp01.get("row");

            for (int i = 0; i < rowArray.size(); i++) {
                JSONObject rowObject = (JSONObject) rowArray.get(i);
                String repNm = (String) rowObject.get("RCP_NM");
                String attFileNoMain = (String) rowObject.get("ATT_FILE_NO_MAIN");

                RecipesDto.ApiMainDto dto = new RecipesDto.ApiMainDto();
                dto.setRCP_NM(repNm);
                dto.setATT_FILE_NO_MAIN(attFileNoMain);

                result.add(dto);
            }

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }


    // 레시피 조회 -> 오픈 api 가져오기 (하나의 레시피 보여주기)
    // http://openapi.foodsafetykorea.go.kr/api/sample/COOKRCP01/xml/1/5/RCP_NM=값
    @GetMapping("/open-api/{RCP_NM}")
    public ResponseEntity<List<RecipesDto.ApiResponseDto>> fetch(@RequestParam String RCP_NM) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://openapi.foodsafetykorea.go.kr/api/e907859720c24072b3be/COOKRCP01/json/1/100/RCP_NM=" + RCP_NM;

        // API에서 JSON 데이터 가져오기
        String jsonString = restTemplate.getForObject(url, String.class);

        JSONParser jsonParser = new JSONParser();
        List<RecipesDto.ApiResponseDto> result = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
            JSONObject cookRcp01 = (JSONObject) jsonObject.get("COOKRCP01");
            JSONArray rowArray = (JSONArray) cookRcp01.get("row");

            for (int i = 0; i < rowArray.size(); i++) {
                JSONObject rowObject = (JSONObject) rowArray.get(i);
                String repNm = (String) rowObject.get("RCP_NM");
                String attFileNoMain = (String) rowObject.get("ATT_FILE_NO_MAIN");
                String rcpWay2 = (String) rowObject.get("RCP_WAY2");
                String rcpPartsDtls = (String) rowObject.get("RCP_PARTS_DTLS");
                String rcpPat2 = (String) rowObject.get("RCP_PAT2");
                String infoEng = (String) rowObject.get("INFO_ENG");
                String hasTag = (String) rowObject.get("HASH_TAG");

                RecipesDto.ApiResponseDto dto = new RecipesDto.ApiResponseDto();
                dto.setRCP_NM(repNm);
                dto.setATT_FILE_NO_MAIN(attFileNoMain);
                dto.setRCP_WAY2(rcpWay2);
                dto.setRCP_PARTS_DTLS(rcpPartsDtls);
                dto.setRCP_PAT2(rcpPat2);
                dto.setINFO_ENG(infoEng);
                dto.setHASH_TAG(hasTag);

                result.add(dto);
            }

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
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
