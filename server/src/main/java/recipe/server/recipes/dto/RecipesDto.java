package recipe.server.recipes.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import recipe.server.utils.PageInfo;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class RecipesDto {

    @Getter
    @Setter
    public class recipesResponseDto {

        private long recipesId;
        private long id;
        private String recipeTitle;
        private String recipeType;
        private String nutrition;
        private String recipeBody;
        private String createAt;
        private String modifiedAt;

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
    public static class recipesPostDto {

        @NotNull
        private long id;

        @NotBlank
        private String recipeTitle;

        @NotBlank
        private String recipeType;

        @NotBlank
        private String nutrition;

        @NotNull
        private String recipeBody;
    }

    @Getter
    @Setter
    public static  class recipesPatchDto {

        @NotNull
        private long id;

        @NotNull
        private long recipesId;

        @NotBlank
        private String recipeTitle;

        @NotBlank
        private String recipeType;

        @NotBlank
        private String nutrition;

        @NotBlank
        private String recipeBody;
    }

    @Getter
    @Setter
    public static class PageResponseDto {

        private List<recipesResponseDto> recipes;
        private PageInfo pageInfo;
    }
}
