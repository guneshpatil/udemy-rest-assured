package utils;

import api.RASpecs;
import data.Constants;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;

public class SharedMapSteps {
    RequestSpecification requestSpecs;

    public RequestSpecification getRequestSpecs() throws FileNotFoundException {
        RestAssured.baseURI = Constants.RSA_WEBSITE;

        requestSpecs = RASpecs.buildMapsSpecsWithLogging();
        return requestSpecs;
    }
}
