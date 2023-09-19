package recipe.server.myPage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import recipe.server.member.entity.Member;

/*

public class MyPageController {

    @PostMapping("/api/store/likes")
    public ResponseEntity<String> likes(long memberId, String saveHeartId, @AuthenticationPrincipal Member member) {

        if(member == null) {
            return ResponseEntity.badRequest().body("회원만 가능합니다");
        }

        long userId = member.getMemberId();
        MyPageService.likes(memberId, saveHeartId, userId);
        return ResponseEntity.ok().body("완료");
    }
}

 */

