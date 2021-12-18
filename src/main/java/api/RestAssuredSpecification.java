package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestAssuredSpecification {
    public RequestSpecification requestSpecs;
    public ResponseSpecification responseSpecs;
    public ResponseSpecification createdStatusSpecs;
    public Cookie cookie;

    public RestAssuredSpecification(String baseUri) {
        //using request specification as a common specification
        requestSpecs = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .build();

        //using response specification as a common specification
        responseSpecs = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        createdStatusSpecs = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
