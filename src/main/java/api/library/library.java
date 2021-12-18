package api.library;

import api.RestAssuredSpecification;
import api.map.Helper;
import data.Constants;
import data.Payload;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class library extends RestAssuredSpecification {
    ArrayList<String> bookIds;

    public library() {
        super("http://216.10.245.166");
    }

    @BeforeSuite
    public void setup() {
        bookIds = new ArrayList<>();
    }

    @Test(dataProvider = "newRandomBooks")
    public void addNewBook(String isbn, String aisle) {
        String addBookResponse = given()
                .spec(requestSpecs)
                .body(Payload.requestBody_addBook(isbn, aisle))
                .when()
                .post(Constants.URL_ADD_BOOK)
                .then()
                .spec(responseSpecs)
                .extract().asString();

        String currentId = Helper.getField(addBookResponse, "ID");
        System.out.println("Book Id added: " + currentId);
        bookIds.add(currentId);
    }

    /***
     * fails only when dataProvider = newRandomBooks
     * work fine for dataProvider = newBooks
     */
    @Test
    public void deleteBooksList() {
        bookIds.forEach(bookId -> {
            System.out.println("Deleting " + bookId);
            given()
                    .spec(requestSpecs)
                    .body((new JSONObject().put("ID", bookId)).toString())
                    .when()
                    .delete(Constants.URL_DELETE_BOOK)
                    .then()
                    .spec(responseSpecs);
        });
    }

    @Ignore
    @Test(dataProvider = "newBooks")
    public void deleteBooks(String isbn, String aisle) {
        System.out.println(given()
                .spec(requestSpecs)
                .body((new JSONObject().put("ID", isbn.concat(aisle))).toString())
                .when()
                .delete(Constants.URL_DELETE_BOOK)
                .then()
                .spec(responseSpecs)
                .extract().body().jsonPath().getString("msg"));
    }

    @DataProvider(name = "newBooks")
    public Object[][] newBooksForLibrary() {
        return new Object[][]{
                {"HGTKDS", "77593380"},
                {"HGTKDS", "77593381"},
                {"HGTKDS", "77593382"}
        };
    }

    @DataProvider(name = "newRandomBooks")
    public Object[][] newRandomBooksForLibrary() {
        String aisle = String.valueOf(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
        aisle = aisle.substring(aisle.length() - 8);
        return new Object[][]{
                {UUID.randomUUID().toString().split("-")[1], aisle},
                {UUID.randomUUID().toString().split("-")[1], aisle},
                {UUID.randomUUID().toString().split("-")[1], aisle}
        };
    }
}
