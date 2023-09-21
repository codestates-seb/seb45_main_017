package recipe.server.recipes.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import recipe.server.member.service.MemberService;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.mapper.RecipesMapper;
import recipe.server.recipes.repository.ImageRepository;
import recipe.server.recipes.service.ImageService;
import recipe.server.recipes.service.RecipesService;


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

import static recipe.server.recipes.dto.RecipesDto.ApiResponseDto.makeApiResponseDto;
import static retrofit2.Response.success;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipesService;
    private final RecipesMapper recipesMapper;
    private final MemberService memberService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @Getter
    @Value("${recipes.openapi-key}")
    private String apikey;


    // TODO : 레시피 찜 -> 마이 페이지
    // TODO : 레시피 검색(필터?)

/*
    // 모든 레시피 보여주기
    @GetMapping("/main/test")
    public ResponseEntity<RecipesDto.mainAndRecipesDto> fetchMainAndRecipes(
            @RequestParam @Positive int pageNumber,
            @RequestParam @Positive int pageSize,
            @RequestParam(required = false) String searchKeyword) {

        // 레시피 데이터 가져오기
        Page<Recipes> recipes;
        if (StringUtils.hasText(searchKeyword)) {
            recipes = recipesService.searchRecipes(searchKeyword, pageNumber, pageSize);
        } else {
            recipes = recipesService.findAllRecipes(pageNumber, pageSize);
        }
        RecipesDto.PageResponseDto recipesPageResponseDto = recipesMapper.recipesPageToPageResponseDto(recipes);

        // 메인 데이터 가져오기
        RestTemplate restTemplate = new RestTemplate();
        String mainUrl = "https://openapi.foodsafetykorea.go.kr/api/e907859720c24072b3be/COOKRCP01/json/1/100";
        String mainJsonString = restTemplate.getForObject(mainUrl, String.class);
        JSONParser jsonParser = new JSONParser();
        List<RecipesDto.ApiMainDto> mainResult = new ArrayList<>();

        try {
            JSONObject mainJsonObject = (JSONObject) jsonParser.parse(mainJsonString);
            JSONObject mainCookRcp01 = (JSONObject) mainJsonObject.get("COOKRCP01");
            JSONArray mainRowArray = (JSONArray) mainCookRcp01.get("row");

            for (int i = 0; i < mainRowArray.size(); i++) {
                JSONObject mainRowObject = (JSONObject) mainRowArray.get(i);
                String repNm = (String) mainRowObject.get("RCP_NM");
                String attFileNoMain = (String) mainRowObject.get("ATT_FILE_NO_MAIN");

                RecipesDto.ApiMainDto dto = new RecipesDto.ApiMainDto();
                dto.setRCP_NM(repNm);
                dto.setATT_FILE_NO_MAIN(attFileNoMain);

                mainResult.add(dto);
            }

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }

        // 조합된 데이터를 하나의 객체로 반환
        RecipesDto.mainAndRecipesDto mainAndRecipesDto = new RecipesDto.mainAndRecipesDto();
        mainAndRecipesDto.setRecipes(recipesPageResponseDto);
        mainAndRecipesDto.setMainData(mainResult);

        return new ResponseEntity<>(mainAndRecipesDto, HttpStatus.OK);
    }

 */



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

    // 레시피 조회 -> 오픈 api 가져오기 (여러 레시피 보여주기)
    @GetMapping("/main")
    public ResponseEntity<List<RecipesDto.ApiMainDto>> fetch() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://openapi.foodsafetykorea.go.kr/api/" + apikey + "/COOKRCP01/json/1/100";

        // API에서 JSON 데이터 가져오기
        String jsonString = restTemplate.getForObject(url, String.class);

        JSONParser jsonParser = new JSONParser();
        List<RecipesDto.ApiMainDto> result = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
            JSONObject cookRcp01 = (JSONObject) jsonObject.get("COOKRCP01");
            JSONArray rowArray = (JSONArray) cookRcp01.get("row");

            for (int i = 0; i <= rowArray.size(); i++) {

                JSONObject rowObject = (JSONObject) rowArray.get(i);

                Long recipesId = (long) (i+1);
                RecipesDto.ApiMainDto dto = RecipesDto.ApiMainDto.mainResponseDto(rowObject, recipesId);
                result.add(dto);
            }

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }


    // 레시피 조회 -> 오픈 api 가져오기 (하나의 레시피 보여주기)
    @GetMapping("/main/{recipesId}")
    public ResponseEntity<RecipesDto.ApiResponseDto> fetchRecipeById(@PathVariable Long recipesId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://openapi.foodsafetykorea.go.kr/api/" + apikey + "/COOKRCP01/json/"+  recipesId +"/"+ recipesId;

        // API에서 JSON 데이터 가져오기
        String jsonString = restTemplate.getForObject(url, String.class);

        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
            JSONObject cookRcp01 = (JSONObject) jsonObject.get("COOKRCP01");
            JSONArray rowArray = (JSONArray) cookRcp01.get("row");

            if (rowArray.size() > 0) {
                JSONObject rowObject = (JSONObject) rowArray.get(0);
                RecipesDto.ApiResponseDto dto = RecipesDto.ApiResponseDto.makeApiResponseDto(rowObject, recipesId);

                // 필요한 데이터를 rowObject에서 추출하여 dto에 설정

                return ResponseEntity.ok(dto);
            } else {
                // 해당 recipesId로 레시피를 찾지 못한 경우
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    // 한 개의 레시피만 조회
    @GetMapping("/{recipe-id}")
    public ResponseEntity getRecipes(@PathVariable("recipe-id") @Positive long recipesId) {

        Recipes recipes = recipesService.findRecipe(recipesId);

        return new ResponseEntity<>(recipesMapper.recipesToRecipesResponse(recipes), HttpStatus.OK);
    }

    /*
    // 모든 레시피 조회
    @GetMapping
    public ResponseEntity findAllRecipes(@RequestParam @Positive int pageNumber,
                                         @RequestParam @Positive int pageSize) {

        Page<Recipes> recipes = recipesService.findAllRecipes(pageNumber, pageSize);
        RecipesDto.PageResponseDto pageResponseDto = recipesMapper.recipesPageToPageResponseDto(recipes);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }
     */


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
}
