package api.jira;

import data.Constants;
import data.Payload;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Challenges {
    Login jiraLogin = new Login();

    @BeforeSuite
    public void setup(){
        jiraLogin.getSessionId();
    }

    @Test
    public void addComment(){
        given().spec(jiraLogin.requestSpecs)
                .pathParam("issueId", "10002")
                .body(Payload.requestBody_addComment())
                .cookie(jiraLogin.cookie)
                .when()
                .post(Constants.URL_ADD_COMMENT)
                .then()
                .spec(jiraLogin.createdStatusSpecs)
                .extract().asString();
    }

}
