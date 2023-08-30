package recipe.server.recipes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.json.simple.JSONObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import recipe.server.utils.PageInfo;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class RecipesDto {

    @Getter
    @Setter
    public static class mainAndRecipesDto {
        private List<RecipesDto.ApiMainDto> mainData;
        private RecipesDto.PageResponseDto recipes;
    }

    @Getter
    @Setter
    public static class recipesResponseDto {

        private Long recipesId;
        private Long memberId;
        private String recipeTitle;
        private String recipeType;
        private String nutrition;
        private String recipeBody;
        private String createAt;
        private String modifiedAt;

        private Long imageId;
        //파일
//        private MultipartFile image;

    }

    @Data
    public static class recipesApiGetDto {

        private String RCP_NM; //메뉴명
        private String RCP_WAY2; //조리방법
        private String RCP_PARTS_DTLS; //재료정보
        private String RCP_PAT2; //요리종류
        private String INFO_ENG; //열량
        private String HASH_TAG; // 해쉬태그
    }

    @Getter
    @Setter
    public static class recipesPostDto {

//        @NotNull
//        private Long memberId;

        @NotBlank
        private String recipeTitle;

        @NotBlank
        private String recipeType;

        @NotBlank
        private String nutrition;

        @NotNull
        private String recipeBody;

        private MultipartFile image;



//        private MultipartFile image;

      //  private String fileName;

      //  private String filePath;
    }

    @Getter
    @Setter
    public static class recipesPatchDto {

        @NotNull
        private Long memberId;

        @NotNull
        private Long recipesId;

        @NotBlank
        private String recipeTitle;

        @NotBlank
        private String recipeType;

        @NotBlank
        private String nutrition;

        @NotBlank
        private String recipeBody;

//        private MultipartFile image;

       // private String fileName;

       // private String filePath;
    }

    @Getter
    @Setter
    public static class PageResponseDto {

        private List<recipesResponseDto> recipes;
        private PageInfo pageInfo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ApiMainDto {
        private String RCP_NM; // 메뉴명
        private String ATT_FILE_NO_MAIN; // 이미지 경로
        private String RCP_PAT2; // 요리종류

        @ElementCollection
        public static ApiMainDto mainResponseDto(JSONObject rowArray) {
            ApiMainDto dto = ApiMainDto.builder()
                    .RCP_NM((String) rowArray.get("RCP_NM"))
                    .ATT_FILE_NO_MAIN((String) rowArray.get("ATT_FILE_NO_MAIN"))
                    .RCP_PAT2((String) rowArray.get("RCP_PAT2"))
                    .build();

            return dto;
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ApiResponseDto {

        private String RCP_NM; // 메뉴명
        private String RCP_WAY2; // 조리방법
        private String RCP_PARTS_DTLS; // 재료정보
        private String RCP_PAT2; // 요리종류
        private String INFO_ENG; // 열량
        private String HASH_TAG; // 해쉬태그

        @JsonIgnore
        private String INFO_CAR; // 탄수화물

        @JsonIgnore
        private String INFO_PRO; // 단백질

        @JsonIgnore
        private String INFO_FAT; // 지방

        @JsonIgnore
        private String INFO_NA; // 나트륨

        private String ATT_FILE_NO_MAIN; // 이미지경로(소)
        private String ATT_FILE_NO_MK; // 이미지경로(대)
        private String MANUAL01; // 만드는법_01
        private String MANUAL_IMG01; // 만드는법_이미지01
        private String MANUAL02; // 만드는법_02
        private String MANUAL_IMG02; // 만드는법_이미지02
        private String MANUAL03; // 만드는법_03
        private String MANUAL_IMG03; // 만드는법_이미지03
        private String MANUAL04; // 만드는법_04
        private String MANUAL_IMG04; // 만드는법_이미지04
        private String MANUAL05; // 만드는법_05
        private String MANUAL_IMG05; // 만드는법_이미지05
        private String MANUAL06; // 만드는법_06
        private String MANUAL_IMG06; // 만드는법_이미지06
        private String MANUAL07; // 만드는법_07
        private String MANUAL_IMG07; // 만드는법_이미지07
        private String MANUAL08; // 만드는법_08
        private String MANUAL_IMG08; // 만드는법_이미지08
        private String MANUAL09; // 만드는법_09
        private String MANUAL_IMG09; // 만드는법_이미지09
        private String MANUAL10; // 만드는법_10
        private String MANUAL_IMG10; // 만드는법_이미지10
        private String MANUAL11; // 만드는법_11
        private String MANUAL_IMG11; // 만드는법_이미지11
        private String MANUAL12; // 만드는법_12
        private String MANUAL_IMG12; // 만드는법_이미지12
        private String MANUAL13; // 만드는법_13
        private String MANUAL_IMG13; // 만드는법_이미지13
        private String MANUAL14; // 만드는법_14
        private String MANUAL_IMG14; // 만드는법_이미지14
        private String MANUAL15; // 만드는법_15
        private String MANUAL_IMG15; // 만드는법_이미지15
        private String MANUAL16; // 만드는법_16
        private String MANUAL_IMG16; // 만드는법_이미지16
        private String MANUAL17; // 만드는법_17
        private String MANUAL_IMG17; // 만드는법_이미지17
        private String MANUAL18; // 만드는법_18
        private String MANUAL_IMG18; // 만드는법_이미지18
        private String MANUAL19; // 만드는법_19
        private String MANUAL_IMG19; // 만드는법_이미지19
        private String MANUAL20; // 만드는법_20
        private String MANUAL_IMG20; // 만드는법_이미지20

        @JsonIgnore
        private String RCP_NA_TIP; // 저감 조리법 tip

        @ElementCollection
        public static ApiResponseDto makeApiResponseDto(JSONObject item) {

            ApiResponseDto dto = ApiResponseDto.builder()
                    .RCP_NM((String) item.get("RCP_NM"))
                    .RCP_WAY2((String) item.get("RCP_WAY2"))
                    .RCP_PARTS_DTLS((String) item.get("RCP_PARTS_DTLS"))
                    .RCP_PAT2((String) item.get("RCP_PAT2"))
                    .INFO_ENG((String) item.get("INFO_ENG"))
                    .HASH_TAG((String) item.get("HASH_TAG"))
                    .ATT_FILE_NO_MAIN((String) item.get("ATT_FILE_NO_MAIN"))
                    .ATT_FILE_NO_MK((String) item.get("ATT_FILE_NO_MK"))
                    .MANUAL01((String) item.get("MANUAL01"))
                    .MANUAL_IMG01((String) item.get("MANUAL_IMG01"))
                    .MANUAL02((String) item.get("MANUAL02"))
                    .MANUAL_IMG02((String) item.get("MANUAL_IMG02"))
                    .MANUAL03((String) item.get("MANUAL03"))
                    .MANUAL_IMG03((String) item.get("MANUAL_IMG03"))
                    .MANUAL04((String) item.get("MANUAL04"))
                    .MANUAL_IMG04((String) item.get("MANUAL_IMG04"))
                    .MANUAL05((String) item.get("MANUAL05"))
                    .MANUAL_IMG05((String) item.get("MANUAL_IMG05"))
                    .MANUAL06((String) item.get("MANUAL06"))
                    .MANUAL_IMG06((String) item.get("MANUAL_IMG06"))
                    .MANUAL07((String) item.get("MANUAL07"))
                    .MANUAL_IMG07((String) item.get("MANUAL_IMG07"))
                    .MANUAL08((String) item.get("MANUAL08"))
                    .MANUAL_IMG08((String) item.get("MANUAL_IMG08"))
                    .MANUAL09((String) item.get("MANUAL09"))
                    .MANUAL_IMG09((String) item.get("MANUAL_IMG09"))
                    .MANUAL10((String) item.get("MANUAL10"))
                    .MANUAL_IMG10((String) item.get("MANUAL_IMG10"))
                    .MANUAL11((String) item.get("MANUAL11"))
                    .MANUAL_IMG11((String) item.get("MANUAL_IMG11"))
                    .MANUAL12((String) item.get("MANUAL12"))
                    .MANUAL_IMG12((String) item.get("MANUAL_IMG12"))
                    .MANUAL13((String) item.get("MANUAL13"))
                    .MANUAL_IMG13((String) item.get("MANUAL_IMG13"))
                    .MANUAL14((String) item.get("MANUAL14"))
                    .MANUAL_IMG14((String) item.get("MANUAL_IMG14"))
                    .MANUAL15((String) item.get("MANUAL15"))
                    .MANUAL_IMG15((String) item.get("MANUAL_IMG15"))
                    .MANUAL16((String) item.get("MANUAL16"))
                    .MANUAL_IMG16((String) item.get("MANUAL_IMG16"))
                    .MANUAL17((String) item.get("MANUAL17"))
                    .MANUAL_IMG17((String) item.get("MANUAL_IMG17"))
                    .MANUAL18((String) item.get("MANUAL18"))
                    .MANUAL_IMG18((String) item.get("MANUAL_IMG18"))
                    .MANUAL19((String) item.get("MANUAL19"))
                    .MANUAL_IMG19((String) item.get("MANUAL_IMG19"))
                    .MANUAL20((String) item.get("MANUAL20"))
                    .MANUAL_IMG20((String) item.get("MANUAL_IMG20"))
                    .build();

            return dto;
        }
    }
}