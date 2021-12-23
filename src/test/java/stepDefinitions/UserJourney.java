package stepDefinitions;

import api.library.Library;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserJourney {
    Library library = new Library();

    @Given("User is on NetBanking landing page")
    public void userIsOnNetBankingLandingPage() {
        library.setup();
    }

    @When("User logs into the application with username and password")
    public void userLogsIntoTheApplicationWithUsernameAndPassword() {
        library.addNewBook("HFOWEJD", "4858");
    }

    @Then("Home Page is populated")
    public void homePageIsPopulated() {
        library.deleteBooks("HFOWEJD", "4858");
    }

    @And("{string} Information is displayed")
    public void informationIsDisplayed(String product) {
        System.out.println(product + " information is displayed!");
    }

}
