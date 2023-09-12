package recipe.server.recipes.service;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import recipe.server.exception.BusinessLogicException;
import retrofit2.Response;

import java.util.Map;

@Component
public class OpenApiManager {

    //https://openapi.foodsafetykorea.go.kr/api/e907859720c24072b3be/COOKRCP01/json/1/100
    private final String BASE_URL = "https://openapi.foodsafetykorea.go.kr";
    private final String apiUrl = "/COOKRCP01";
    private final String serviceKey = "e907859720c24072b3be";
    private final String defaultQueryParam = "type=json";
    private final String numOfRows = "numOfRows=100";
    private final String areaCode = "areaCode=1";

    private String makeUrl() throws BusinessLogicException {
        String queryString = String.join("&", serviceKey, defaultQueryParam, numOfRows, areaCode);
        return BASE_URL + apiUrl + "?" + queryString;
    }

    public ResponseEntity<?> fetch() throws BusinessLogicException {

        System.out.println(makeUrl());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> resultMap = restTemplate.exchange(makeUrl(), HttpMethod.GET, entity, Map.class);
        System.out.println(resultMap.getBody());
        return resultMap;
    }

    public ResponseEntity<?> convertRetrofitResponse(Response<?> retrofitResponse) {
        if (retrofitResponse.isSuccessful()) {
            return ResponseEntity.ok(retrofitResponse.body());
        } else {
            return ResponseEntity.status(HttpStatus.valueOf(retrofitResponse.code())).body(retrofitResponse.errorBody());
        }
    }
}
