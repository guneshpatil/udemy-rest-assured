package api.map;

import data.Constants;
import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Helper {

    public static String addPlace(HashMap<String, String> queryParams, String body) {
        return given().queryParams(queryParams)
                .header("Content-Type", "application/json")
                .body(body)
                .when().post(Constants.URL_ADD_PLACE)
                .then()
                .assertThat().statusCode(200)
                .header("server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();
    }

    public static void updatePlace(HashMap<String, String> queryParams, String body) {
        given().queryParams(queryParams)
                .header("Content-Type", "application/json")
                .body(body)
                .when().put(Constants.URL_UPDATE_PLACE)
                .then()
                .assertThat().statusCode(200)
                .extract().asString();
    }

    public static String getPlace(HashMap<String, String> queryParams) {
        return given().queryParams(queryParams)
                .header("Content-Type", "application/json")
                .when()
                .get(Constants.URL_GET_PLACE)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().asString();
    }

    public static <T> T getField(String response, String fieldPath) {
        return ((T) new JsonPath(response).get(fieldPath));
    }

    public static <T> List<T> getCollection(String response, String path) {
        return new JsonPath(response).getList(path);
    }
}
