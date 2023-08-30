package recipe.server.auth.handler;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        log.error("Failed: {}", exception.getMessage());

        String errorMessage = "Authentication(User not found) failed";

        sendErrorResponse(response, errorMessage);

}


    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized

        response.setContentType("application/json");

        JsonObject errorJson = new JsonObject();
        errorJson.addProperty("error", errorMessage);

        String errorJsonString = errorJson.toString();

        PrintWriter writer = response.getWriter();
        writer.write(errorJsonString);
        writer.flush();
    }
}


//     실패 예외처리
//    private void sendErrorResponse(HttpServletResponse response) throws IOException {
//
//        Gson gson = new Gson();
//
//    }