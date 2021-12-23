package stepDefinitions;

import api.map.Helper;
import api.map.MapWithPojo;
import api.map.pojo.AddPlace;
import data.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import utils.MapApiResources;
import utils.SharedMapSteps;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MapSteps extends SharedMapSteps {
    AddPlace addPlace_payload;
    static Map<String, String> extractedValues = new HashMap<>();
    Response response;
    RequestSpecification currentRequest;

    @Given("New place information is available")
    public void newPlaceInformationIsAvailable() {
        addPlace_payload = MapWithPojo.getNewPayload();
    }

    @And("{string} is {string}")
    public void is(String fieldName, String value) {
        switch (fieldName) {
            case "name" -> addPlace_payload.setName(value);
            case "address" -> addPlace_payload.setAddress(value);
            case "language" -> addPlace_payload.setLanguage(value);
        }
    }

    @And("modified object is added to the request")
    public void modifiedObjectIsAddedToTheRequest() throws FileNotFoundException {
        currentRequest = given().spec(getRequestSpecs()).body(addPlace_payload);
    }

    @When("User calls {string} with {string} call")
    public void userCallsResource(String methodName, String methodType) {
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

    @And("verify {string} for created place using {string}")
    public void verifyForCreatedPlaceUsing(String placeName, String getApiUrl) throws FileNotFoundException {
        currentRequest = given().spec(getRequestSpecs())
                .queryParams(Constants.PLACE_ID, extractedValues.get(Constants.PLACE_ID));
        userCallsResource(getApiUrl, "GET");
        Assert.assertEquals(Helper.getField(response.asString(), "name"),
                placeName);
    }

    @And("extract {string} from the response")
    public void extractFromTheResponse(String fieldName) {
        extractedValues.put(fieldName, Helper.getField(response.asString(), fieldName));
    }

    @Given("Delete place request is compiled")
    public void deletePlaceRequestIsCompiled() throws FileNotFoundException {
        JSONObject deleteBody = new JSONObject();
        deleteBody.put(Constants.PLACE_ID, extractedValues.get(Constants.PLACE_ID));
        currentRequest = given().spec(getRequestSpecs())
                .body(deleteBody.toString());
    }
}
