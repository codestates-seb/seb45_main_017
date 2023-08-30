package recipe.server.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class recipesController {

    // 레시피 작성 (로그인 시)
    @PostMapping
    public ResponseEntity postRecipes() {

        return null;
    }

    // 레시피 조회

    // 레시피 삭제 (로그인 시 -> 자신이 작성한 레시피만)
    // 레시피 수정 (로그인 시 -> 자신이 작성한 레시피만)
    // 레시피 검색
    // 레시피 찜 (로그인 시 / 마이페이지에서 확인 가능)
}
