package api.library;

import api.map.Helper;
import data.Constants;
import data.Payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class library {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    ArrayList<String> bookIds;

    @BeforeSuite
    public void setup(){
        //using request specification as a common specification
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://216.10.245.166")
                .setContentType(ContentType.JSON)
                .build();

        //using response specification as a common specification
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        bookIds = new ArrayList<>();
    }

    @Test(dataProvider="newRandomBooks")
    public void addNewBook(String isbn, String aisle){
        String addBookResponse = given()
                .spec(requestSpecification)
                .body(Payload.requestBody_addBook(isbn, aisle))
                .when()
                .post(Constants.URL_ADD_BOOK)
                .then().log().body()
                .spec(responseSpecification)
                .extract().asString();

        bookIds.add(Helper.getField(addBookResponse, "ID"));
    }

    /***
     * fails only when dataProvider = newRandomBooks
     * work fine for dataProvider = newBooks
     */
    @Test
    public void deleteBooksList(){
        bookIds.forEach(bookId ->
            given().log().body()
                    .spec(requestSpecification)
                    .body((new JSONObject().put("ID", bookId)).toString())
                    .when()
                    .delete(Constants.URL_DELETE_BOOK)
                    .then().log().body()
                    .spec(responseSpecification));
    }

    @Ignore
    @Test(dataProvider = "newBooks")
    public void deleteBooks(String isbn, String aisle){
            System.out.println(given()
                    .spec(requestSpecification)
                    .body((new JSONObject().put("ID", isbn.concat(aisle))).toString())
                    .when()
                    .delete(Constants.URL_DELETE_BOOK)
                    .then()
                    .spec(responseSpecification)
                    .extract().body().jsonPath().getString("msg"));
    }

    @DataProvider(name="newBooks")
    public Object[][] newBooksForLibrary(){
        return new Object[][]{
                {"HGTKDS", "77593380"},
                {"HGTKDS", "77593381"},
                {"HGTKDS", "77593382"}
        };
    }

    @DataProvider(name="newRandomBooks")
    public Object[][] newRandomBooksForLibrary(){
        String aisle = String.valueOf(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
        aisle = aisle.substring(aisle.length() - 8);
        return new Object[][]{
                {aisle, UUID.randomUUID().toString().split("-")[1]},
                {aisle, UUID.randomUUID().toString().split("-")[1]},
                {aisle, UUID.randomUUID().toString().split("-")[1]}
        };
    }
}
