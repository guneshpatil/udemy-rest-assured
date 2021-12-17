package api.library;

import api.map.Helper;
import data.Payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class library {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String bookId;

    @BeforeSuite
    public void setup(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://216.10.245.166")
                .setContentType(ContentType.JSON)
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void addNewBook(){
        String addBookResponse = given().spec(requestSpecification)
                .body(Payload.requestBody_addBook())
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .extract().asString();

        bookId = Helper.getField(addBookResponse, "ID");
    }
}
