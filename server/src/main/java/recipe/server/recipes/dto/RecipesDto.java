package recipe.server.recipes.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
       // private String createAt;
       // private String modifiedAt;

    }

    /*
    @Getter
    public static class recipesGetDto {

        @NotBlank
        private String startIdx; //요청시작위치
        @NotBlank
        private String endIdx; //요청종료위치

        private String RCP_NM; //메뉴명

        private String RCP_PARTS_DTLS; //재료정보

        private String RCP_PAT2; //요리종류
    }

     */

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
}
