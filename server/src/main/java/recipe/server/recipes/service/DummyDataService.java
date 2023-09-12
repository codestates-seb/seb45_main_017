package recipe.server.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import recipe.server.recipes.controller.ImageHandler;
import recipe.server.recipes.entity.Recipe;
import recipe.server.recipes.entity.Recipes;
import recipe.server.recipes.repository.RecipeRepository;
import recipe.server.recipes.repository.RecipesRepository;

import javax.annotation.PostConstruct;

// 데이터 확인용 서비스
// 삭제 예정
@Service
public class DummyDataService {
    private final RecipeRepository recipeRepository;


   // private final ImageHandler imageHandler;

    @Autowired
    public DummyDataService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
       // this.imageHandler = new ImageHandler();
    }

    /*
    public Recipes addRecipes(
            Recipes recipes, List<imageList>
    )

     */
    @PostConstruct
    public void initializeDummyData() {
        // 여기서 더미 데이터 생성 및 저장
        Recipe recipe1 = new Recipe("마들렌", "빵", "250", "반죽,굽기");
        Recipe recipe2 = new Recipe("햄버거", "빵", "400", "빵 토스팅, 야채,소스 추가");
        Recipe recipe3 = new Recipe("밥", "한식", "200", "쌀 불리기, 밥 하기");

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);
    }
}
