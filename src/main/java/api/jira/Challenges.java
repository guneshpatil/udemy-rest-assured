package api.jira;

import data.Constants;
import data.Payload;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class Challenges {
    Login jiraLogin = new Login();

    @BeforeSuite
    public void setup() {
        jiraLogin.getSessionId();
    }

    @Test
    public void a_addComment() {
        given().spec(jiraLogin.requestSpecs)
                .pathParam("issueId", "10002")
                .body(Payload.requestBody_addComment().replace("COMMENT_ID", UUID.randomUUID().toString()))
                .filter(jiraLogin.sessionFilter)
                .when()
                .post(Constants.URL_JIRA_ADD_COMMENT)
                .then()
                .spec(jiraLogin.createdStatusSpecs)
                .extract().asString();
    }

    @Ignore
    @Test
    public void b_addAttachment() throws URISyntaxException {
        given().spec(jiraLogin.requestSpecs)
                .pathParam("issueId", "10002")
                .header("X-Atlassian-Token", "no-check")
                .contentType(ContentType.MULTIPART)
                .filter(jiraLogin.sessionFilter)
                .multiPart("file", new File(Objects.requireNonNull(getClass().getClassLoader().getResource("attachment.txt")).toURI()))
                .when()
                .post(Constants.URL_JIRA_ADD_FILE)
                .then()
                .spec(jiraLogin.responseSpecs);
    }

    @Test
    public void c_getIssueDetails(){
        given().spec(jiraLogin.requestSpecs)
                .pathParam("issueId", "10002")
                .filter(jiraLogin.sessionFilter)
                .when()
                .get(Constants.URL_JIRA_GET_ISSUE_DETAILS)
                .then().log().body()
                .spec(jiraLogin.responseSpecs);
    }
}
