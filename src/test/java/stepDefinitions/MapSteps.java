package stepDefinitions;

import api.map.MapWithPojo;
import api.map.pojo.AddPlace;
import data.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.MapApiResources;
import utils.SharedMapSteps;

import java.io.FileNotFoundException;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class MapSteps extends SharedMapSteps {
    AddPlace addPlace_payload;
    Response response;
    RequestSpecification currentRequest;

    @Given("New place information is available")
    public void newPlaceInformationIsAvailable() throws FileNotFoundException {
        addPlace_payload = MapWithPojo.getNewPayload();
        currentRequest = given().spec(getRequestSpecs()).body(addPlace_payload);
    }

    @When("User calls {string} with {string} call")
    public void userCallsWith(String methodName, String methodType) {
        switch (methodType.toUpperCase()) {
            case "POST" -> response = currentRequest.when()
                    .post(MapApiResources.valueOf(methodName).getResource());
            case "GET" -> response = currentRequest.when()
                    .get(MapApiResources.valueOf(methodName).getResource());
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
