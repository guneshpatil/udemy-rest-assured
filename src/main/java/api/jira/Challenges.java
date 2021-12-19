package api.jira;

import api.map.Helper;
import data.Constants;
import data.Payload;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static api.map.Helper.getCollection;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Challenges {
    Login jiraLogin = new Login();
    String commentId;

    @BeforeSuite
    public void setup() {
        jiraLogin.getSessionId();
    }

    @Test
    public void a_addComment() {
        commentId = given().spec(jiraLogin.requestSpecs)
                .pathParam("issueId", "10002")
                .body(Payload.requestBody_addComment().replace("COMMENT_ID", UUID.randomUUID().toString()))
                .filter(jiraLogin.sessionFilter)
                .when()
                .post(Constants.URL_JIRA_ADD_COMMENT)
                .then()
                .spec(jiraLogin.createdStatusSpecs)
                .extract().response().body().jsonPath().getString("id");
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
        String response = given().spec(jiraLogin.requestSpecs)
                .pathParam("issueId", "10002")
                .queryParam("fields", "comment,issuetype")
                .filter(jiraLogin.sessionFilter)
                .when()
                .get(Constants.URL_JIRA_GET_ISSUE_DETAILS)
                .then()
                .spec(jiraLogin.responseSpecs)
                .assertThat()
                .body("fields.comment.comments.id", hasItem(commentId))
                .extract().response().asString();

        //another way of validating a particular value in an array by iterating
        ArrayList<String> commentsIds = new ArrayList<>(Helper.getCollection(response, "fields.comment.comments.id"));
        Assert.assertTrue(commentsIds.contains(commentId));
    }
}
