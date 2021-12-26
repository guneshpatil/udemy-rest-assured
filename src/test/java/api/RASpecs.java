package api;

import data.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class RASpecs {
    public RequestSpecification requestSpecs;
    public static RequestSpecification loggingRequestSpecs;
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

    public static RequestSpecification buildMapsSpecs(String baseUri) {
        //using request specification as a common specification
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addQueryParam("key", Constants.KEY_TEXT)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification buildMapsSpecs() {
        //using request specification as a common specification
        return new RequestSpecBuilder()
                .addQueryParam("key", Constants.KEY_TEXT)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification buildMapsSpecsWithLogging() throws FileNotFoundException {
        if (Objects.isNull(loggingRequestSpecs)) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            //using request specification as a common specification
            loggingRequestSpecs = new RequestSpecBuilder()
                    .addQueryParam("key", Constants.KEY_TEXT)
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
        }
        return loggingRequestSpecs;
    }

    public static ResponseSpecification getResponseSpecs() {
        //using response specification as a common specification
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
