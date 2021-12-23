package stepDefinitions;

import api.RASpecs;
import data.Constants;
import data.Payload;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class MapSteps {
    JSONObject addPlace_payload;
    Response response;

    @Given("New place information is available")
    public void newPlaceInformationIsAvailable() {
        addPlace_payload = new JSONObject(Payload.requestBody_add());
        String name = addPlace_payload.getString("name");
        addPlace_payload.put("name", name + " " + UUID.randomUUID());
    }

    @When("User calls {string} with Post call")
    public void userCallsWith(String arg0) {
        response = given().spec(RASpecs.buildMapsSpecs(Constants.RSA_WEBSITE))
                .body(addPlace_payload.toString())
                .when()
                .post(Constants.URL_ADD_PLACE)
                .then().log().all()
                .extract().response();
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
}
