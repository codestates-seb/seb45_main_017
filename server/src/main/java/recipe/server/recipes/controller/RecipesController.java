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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import recipe.server.member.service.MemberService;
import recipe.server.recipes.dto.RecipesDto;
import recipe.server.recipes.entity.Recipe;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.mapper.RecipesMapper;
import recipe.server.recipes.repository.ImageRepository;
import recipe.server.recipes.repository.RecipeRepository;
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

import static retrofit2.Response.success;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipesService;
    private final RecipesMapper recipesMapper;
    private final RecipeRepository recipeRepository;
   // private final OpenApiManager openApiManager;
    private final MemberService memberService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @Getter
    @Value("${recipes.openapi-key}")
    private String apikey;


    // TODO : 레시피 찜 -> 마이 페이지
    // TODO : 레시피 검색(필터?)
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

    @GetMapping("/main/{RCP_NM}")
    public ResponseEntity<List<RecipesDto.ApiResponseDto>> fetch(@RequestParam String RCP_NM) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://openapi.foodsafetykorea.go.kr/api/" + apikey + "/COOKRCP01/json/1/100/RCP_NM=" + RCP_NM;

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
                String AttFileNoMk = (String) rowObject.get("ATT_FILE_NO_MAIN");

                String manual01 = (String) rowObject.get("MANUAL01");
                String manualImg01 = (String) rowObject.get("MANUAL_IMG01");

                String manual02 = (String) rowObject.get("MANUAL02");
                String manualImg02 = (String) rowObject.get("MANUAL_IMG02");

                String manual03 = (String) rowObject.get("MANUAL03");
                String manualImg03 = (String) rowObject.get("MANUAL_IMG03");

                String manual04 = (String) rowObject.get("MANUAL04");
                String manualImg04 = (String) rowObject.get("MANUAL_IMG04");

                String manual05 = (String) rowObject.get("MANUAL05");
                String manualImg05 = (String) rowObject.get("MANUAL_IMG05");

                String manual06 = (String) rowObject.get("MANUAL06");
                String manualImg06 = (String) rowObject.get("MANUAL_IMG06");

                String manual07 = (String) rowObject.get("MANUAL07");
                String manualImg07 = (String) rowObject.get("MANUAL_IMG07");

                String manual08 = (String) rowObject.get("MANUAL08");
                String manualImg08 = (String) rowObject.get("MANUAL_IMG08");

                String manual09 = (String) rowObject.get("MANUAL09");
                String manualImg09 = (String) rowObject.get("MANUAL_IMG09");

                String manual10 = (String) rowObject.get("MANUAL10");
                String manualImg10 = (String) rowObject.get("MANUAL_IMG10");

                String manual11 = (String) rowObject.get("MANUAL11");
                String manualImg11 = (String) rowObject.get("MANUAL_IMG11");

                String manual12 = (String) rowObject.get("MANUAL12");
                String manualImg12 = (String) rowObject.get("MANUAL_IMG12");

                String manual13 = (String) rowObject.get("MANUAL13");
                String manualImg13 = (String) rowObject.get("MANUAL_IMG13");

                String manual14 = (String) rowObject.get("MANUAL14");
                String manualImg14 = (String) rowObject.get("MANUAL_IMG14");

                String manual15 = (String) rowObject.get("MANUAL15");
                String manualImg15 = (String) rowObject.get("MANUAL_IMG15");

                String manual16 = (String) rowObject.get("MANUAL16");
                String manualImg16 = (String) rowObject.get("MANUAL_IMG16");

                String manual17 = (String) rowObject.get("MANUAL17");
                String manualImg17 = (String) rowObject.get("MANUAL_IMG17");

                String manual18 = (String) rowObject.get("MANUAL18");
                String manualImg18 = (String) rowObject.get("MANUAL_IMG18");

                String manual19 = (String) rowObject.get("MANUAL19");
                String manualImg19 = (String) rowObject.get("MANUAL_IMG19");

                String manual20 = (String) rowObject.get("MANUAL20");
                String manualImg20 = (String) rowObject.get("MANUAL_IMG20");

                RecipesDto.ApiResponseDto dto = new RecipesDto.ApiResponseDto();
                dto.setRCP_NM(repNm);
                dto.setATT_FILE_NO_MAIN(attFileNoMain);
                dto.setRCP_WAY2(rcpWay2);
                dto.setRCP_PARTS_DTLS(rcpPartsDtls);
                dto.setRCP_PAT2(rcpPat2);
                dto.setINFO_ENG(infoEng);
                dto.setHASH_TAG(hasTag);
                dto.setATT_FILE_NO_MAIN(AttFileNoMk);

                dto.setMANUAL01(manual01);
                dto.setMANUAL_IMG01(manualImg01);

                dto.setMANUAL02(manual02);
                dto.setMANUAL_IMG02(manualImg02);

                dto.setMANUAL03(manual03);
                dto.setMANUAL_IMG03(manualImg03);

                dto.setMANUAL04(manual04);
                dto.setMANUAL_IMG04(manualImg04);

                dto.setMANUAL05(manual05);
                dto.setMANUAL_IMG05(manualImg05);

                dto.setMANUAL06(manual06);
                dto.setMANUAL_IMG06(manualImg06);

                dto.setMANUAL07(manual07);
                dto.setMANUAL_IMG07(manualImg07);

                dto.setMANUAL08(manual08);
                dto.setMANUAL_IMG08(manualImg08);

                dto.setMANUAL09(manual09);
                dto.setMANUAL_IMG09(manualImg09);

                dto.setMANUAL10(manual10);
                dto.setMANUAL_IMG10(manualImg10);

                dto.setMANUAL11(manual11);
                dto.setMANUAL_IMG11(manualImg11);

                dto.setMANUAL12(manual12);
                dto.setMANUAL_IMG12(manualImg12);

                dto.setMANUAL13(manual13);
                dto.setMANUAL_IMG13(manualImg13);

                dto.setMANUAL14(manual14);
                dto.setMANUAL_IMG14(manualImg14);

                dto.setMANUAL15(manual15);
                dto.setMANUAL_IMG15(manualImg15);

                dto.setMANUAL16(manual16);
                dto.setMANUAL_IMG16(manualImg16);

                dto.setMANUAL17(manual17);
                dto.setMANUAL_IMG17(manualImg17);

                dto.setMANUAL18(manual18);
                dto.setMANUAL_IMG18(manualImg18);

                dto.setMANUAL19(manual19);
                dto.setMANUAL_IMG19(manualImg19);

                dto.setMANUAL20(manual20);
                dto.setMANUAL_IMG20(manualImg20);

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
//    @GetMapping
//    public ResponseEntity findAllRecipes(@RequestParam @Positive int pageNumber,
//                                         @RequestParam @Positive int pageSize) {
//
//        Page<Recipes> recipes = recipesService.findAllRecipes(pageNumber, pageSize);
//        RecipesDto.PageResponseDto pageResponseDto = recipesMapper.recipesPageToPageResponseDto(recipes);
//        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
//    }


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
