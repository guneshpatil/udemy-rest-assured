package api.map;

import api.RASpecs;
import api.map.pojo.AddPlace;
import api.map.pojo.Location;
import data.Constants;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class MapWithPojo extends RASpecs {
    private AddPlace addPlacePayload;
    private String currentLocationId;

    public MapWithPojo() {
        super(Constants.RSA_WEBSITE);
    }

    @BeforeSuite
    public void setup() {
        addPlacePayload = new AddPlace();
        addPlacePayload.setName("POJO Location #" + UUID.randomUUID());
        addPlacePayload.setAddress("49, Sunset Drive");
        addPlacePayload.setAccuracy(23);
        addPlacePayload.setLanguage("Marathi");
        addPlacePayload.setLocation(new Location(23.4395038, -54.959604968));
        addPlacePayload.setPhoneNumber("+91 437593489");
        List<String> types = new ArrayList<>();
        types.add("Fiction");
        types.add("Work");
        addPlacePayload.setTypes(types);
        addPlacePayload.setWebsite("https://www.endgame.com");
    }

    @Test
    public void addNewPlace() {
        currentLocationId = given().spec(buildMapsSpecs(Constants.RSA_WEBSITE))
                .body(addPlacePayload)
                .when().post(Constants.URL_ADD_PLACE)
                .then().log().body()
                .spec(responseSpecs)
                .extract().body().jsonPath().getString("place_id");
    }

    @Test
    public void getNewPlace() {
        String currentPlace = given().spec(buildMapsSpecs(Constants.RSA_WEBSITE))
                .queryParams("place_id", currentLocationId)
                .when().get(Constants.URL_GET_PLACE).then().log().all()
                .extract().asString();

        System.out.println(new JSONObject(currentPlace).get("name"));
    }

    public static AddPlace getNewPayload() {
        AddPlace addPlacePayload = new AddPlace();
        addPlacePayload.setName("POJO Location #" + UUID.randomUUID());
        addPlacePayload.setAddress("49, Sunset Drive");
        addPlacePayload.setAccuracy(23);
        addPlacePayload.setLanguage("Marathi");
        addPlacePayload.setLocation(new Location(23.4395038, -54.959604968));
        addPlacePayload.setPhoneNumber("+91 437593489");
        List<String> types = new ArrayList<>();
        types.add("Fiction");
        types.add("Work");
        addPlacePayload.setTypes(types);
        addPlacePayload.setWebsite("https://www.endgame.com");
        return addPlacePayload;
    }
}
