package api.map;

import data.Constants;
import data.Payload;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Basics {

    private static String place_id;
    private static HashMap<String, String> queryParams = new HashMap<>();

    public static void main(String[] args) {
        queryParams.put("key", Constants.KEY_TEXT);
        //validate the Add Place API
        //given - inputs, when - submit - resource and http method, then - validate
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = Helper.addPlace(queryParams, Payload.requestBody_add());

        //add place, update with new address, get place and validate new address
        place_id = Helper.getField(response, "place_id");

        JSONObject updateRequestPayload = new JSONObject(Payload.requestBody_updateWithNewAddress());
        updateRequestPayload.put("place_id", place_id);

        Helper.updatePlace(queryParams, updateRequestPayload.toString());

        queryParams.put("place_id", place_id);
        String updatedRecord = Helper.getPlace(queryParams);

        Assert.assertEquals(Helper.getField(updatedRecord, "address"), Constants.NEW_ADDRESS);
    }
}
