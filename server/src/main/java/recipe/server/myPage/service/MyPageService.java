import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/*
@RequiredArgsConstructor
@Service
@Transactional
public class MyPageService {

    @Transactional
    public void likes(long recipesId, String likes, long userId) {
        Map<String, Long> map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("userId", userId);

        if(likes.equals("on")) {
            storeMapper.addLikes(map);
        } else {
            storeMapper.deleteLikes(map);
        }
}

 */