package recipe.server.recipes.dto;

import org.json.simple.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;

public class OpenApiDto {
    private String RCP_NM; //메뉴명
    private String RCP_WAY2; //조리방법
    private String RCP_PARTS_DTLS; //재료정보
    private String RCP_PAT2; //요리종류
    private String INFO_ENG; //열량
    private String HASH_TAG; // 해쉬태그

    /*
    private OpenApiDto makeLocationDto(JSONObject item) {

        OpenApiDto dto = OpenApiDto.builder().
                title((String) item.get("RCP_NM")).
                build();
        return dto;
    }

    public List<OpenApiDto> fetch() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(makeUrl(), String.class);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        // 가장 큰 JSON 객체 response 가져오기
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");

        // 그 다음 body 부분 파싱
        JSONObject jsonBody = (JSONObject) jsonResponse.get("body");

        // 그 다음 위치 정보를 배열로 담은 items 파싱
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");

        // items는 JSON임, 이제 그걸 또 배열로 가져온다
        JSONArray jsonItemList = (JSONArray) jsonItems.get("item");

        List<OpenApiDto> result = new ArrayList<>();

        for (Object o : jsonItemList) {
            JSONObject item = (JSONObject) o;
            result.add(makeLocationDto(item));
        }
        return result;

    }

     */
}
