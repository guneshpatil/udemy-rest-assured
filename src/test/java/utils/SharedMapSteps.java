package utils;

import api.RASpecs;
import data.Constants;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class SharedMapSteps {
    RequestSpecification requestSpecs;

    public RequestSpecification getRequestSpecs() {
        RestAssured.baseURI = Constants.RSA_WEBSITE;

        requestSpecs = RASpecs.buildMapsSpecs();
        return requestSpecs;
    }
}
