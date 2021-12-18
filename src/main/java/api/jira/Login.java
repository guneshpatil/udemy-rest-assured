package api.jira;

import api.RestAssuredSpecification;
import data.Constants;
import data.LoginDetails;
import data.Payload;
import io.restassured.http.Cookie;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Login extends RestAssuredSpecification {

    public Login() {
        super("http://localhost:8080");
    }

    public void getSessionId(){
        JSONObject session = new JSONObject(given().spec(requestSpecs)
                .body(LoginDetails.requestBody_authenticate())
                .when()
                .post(Constants.URL_COOKIE_LOGIN)
                .then().spec(responseSpecs)
                .extract().body().asString()).getJSONObject("session");
        cookie = new Cookie.Builder(session.getString("name"), session.getString("value")).build();
    }
}
