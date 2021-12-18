package api.jira;

import api.RASpecs;
import data.Constants;
import data.LoginDetails;

import static io.restassured.RestAssured.given;

public class Login extends RASpecs {

    public Login() {
        super("http://localhost:8080");
    }

    public void getSessionId() {
        given().spec(requestSpecs)
                .body(LoginDetails.requestBody_authenticate())
                .when()
                .filter(sessionFilter)
                .post(Constants.URL_COOKIE_LOGIN)
                .then().spec(responseSpecs);
    }
}
