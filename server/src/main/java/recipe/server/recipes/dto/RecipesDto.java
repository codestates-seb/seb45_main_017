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
        private String recipeBody;
        private String createAt;
        private String modifiedAt;

    }

    @Getter
    public static class recipesPostDto {

        @NotNull
        private long id;

        @NotBlank
        private String recipeTitle;

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
        private String recipeBody;
    }
}
