package runner;

import api.RASpecs;
import data.Constants;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Before;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinitions",
        tags = "@Maps"
)

public class TestRunner {
    @Before
    public void restAssuredSetup(){
    }
}
