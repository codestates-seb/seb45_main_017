package recipe.server.recipes.controller;

//import org.json.JSONObject;
//import org.json.XML;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
@RestController
public class RecipesApiController {

    @GetMapping("/jsonapi")
    public String callApiWithJson() {

        StringBuffer result = new StringBuffer();

        try {
            String apiUrl = "https://openapi.foodsafetykorea.go.kr/api?" +
                    "serviceKey= " +
                    "pageNo = 0" +
                    "&numOfRows = 0" +
                    "&type = json";

            URL url = new URL(apiUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;

            while ((returnLine = br.readLine()) != null) {

                result.append(returnLine + "\n\r");
            }

            urlConnection.disconnect();

            return result.toString();
        }
    }

    public void init(String jsonData) {

        try {

            JSONObject jOb1;
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonData);
            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");
            JSONArray array = (JSONArray) parseBody.get("items");

            for(int i = 0; i < array.size(); i++){

            }
    }
}

 */

