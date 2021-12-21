package api.oauth;

import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import data.Constants;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.oauth.OAuthHelper.getCodeOAuthParams;
import static api.oauth.OAuthHelper.getTokenOAuthParams;
import static io.restassured.RestAssured.given;

public class GoogleOAuth {

    private String access_token;

    @Test
    public void getAccessToken(){
        access_token = given().queryParams(getTokenOAuthParams())
                .when()
                .post(Constants.URL_GOOGLE_TOKEN)
                .then().log().body()
                .extract().body().jsonPath().getString("access_token");
    }

    @Test
    public void getCourses(){
        given().queryParam("access_token", access_token)
                .when()
                .get(Constants.URL_RSA_GET_COURSE_PHP)
                .then().log().body();
    }
}
