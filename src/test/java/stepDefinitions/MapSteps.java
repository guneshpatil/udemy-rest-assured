package stepDefinitions;

import api.map.MapWithPojo;
import api.map.pojo.AddPlace;
import data.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.SharedMapSteps;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class MapSteps extends SharedMapSteps {
    AddPlace addPlace_payload;
    Response response;

    @Given("New place information is available")
    public void newPlaceInformationIsAvailable() {
        addPlace_payload = MapWithPojo.getNewPayload();
    }

    @When("User calls {string} with Post call")
    public void userCallsWith(String methodName) throws FileNotFoundException {
        switch (methodName) {
            case "AddPlaceAPI":
                response = given().spec(getRequestSpecs())
                        .body(addPlace_payload)
                        .when()
                        .post(Constants.URL_ADD_PLACE)
                        .then()
                        .extract().response();
                break;
            case "deletePlaceAPI":
                break;
        }
    }

    @Then("the API call responds with code {int}")
    public void theAPICallRespondsWithCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @And("{string} in response is {string}")
    public void ofResponseIs(String field, String value) {
        Assert.assertEquals(
                response.jsonPath().getString(field),
                value);
    }

    @And("{string} is {string}")
    public void is(String fieldName, String value) {
        switch (fieldName) {
            case "name" -> addPlace_payload.setName(value);
            case "address" -> addPlace_payload.setAddress(value);
            case "language" -> addPlace_payload.setLanguage(value);
        }
    }
}
