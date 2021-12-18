package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RASpecs {
    public RequestSpecification requestSpecs;
    public ResponseSpecification responseSpecs;
    public ResponseSpecification createdStatusSpecs;
    public SessionFilter sessionFilter = new SessionFilter();

    public RASpecs(String baseUri) {
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
