package api.oauth;

import data.Constants;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static api.oauth.OAuthHelper.getTokenOAuthParams;
import static io.restassured.RestAssured.given;

public class GoogleOAuth {

    private String access_token, auth_code;

    @Ignore
    @BeforeSuite
    public void getCode() {
//        System.setProperty("webdriver.chrome.driver", "C:/Users/IBMADMIN/code/chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
////        chromeOptions.addArguments("--incognito");
//        WebDriver driver = new ChromeDriver(chromeOptions);
//
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//        driver.findElement(By.cssSelector("input[type='email'")).sendKeys(LoginDetails.GOOGLE_USER);
//        Thread.sleep(1000);
//        driver.findElement(By.cssSelector("input[type='email'")).sendKeys(Keys.ENTER);
//        Thread.sleep(5000);
//        if(driver.findElement(By.cssSelector("[jsname='LgbsSe']")).isDisplayed()){
//            driver.findElement(By.cssSelector("[jsname='LgbsSe']")).click();
//            driver.findElement(By.cssSelector("input[type='email'")).sendKeys(LoginDetails.GOOGLE_USER);
//            Thread.sleep(1000);
//            driver.findElement(By.cssSelector("input[type='email'")).sendKeys(Keys.ENTER);
//            Thread.sleep(5000);
//        }
//        driver.findElement(By.cssSelector("input[type='password'")).sendKeys(LoginDetails.GOOGLE_PASSWORD);
//        Thread.sleep(1000);
//        driver.findElement(By.cssSelector("input[type='password'")).sendKeys(Keys.ENTER);
//        Thread.sleep(4000);
//        driver.getCurrentUrl();
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjjvOlMTITIpINw_Bq7ZSHnC4P7cZWAQqt0NXRDBU47RWyvNqMf7OaiKZuSr6I24g&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none#";
//        auth_code = url.split("code=")[1].split("&scope")[0];
        auth_code = "4%2F0AX4XfWgydfFNmFqriAg5D-hETyWYmEbXkp0w6ptc18bM481KPRTwbmpZWDxh8iVFhbqHJw";
    }

    @Test
    public void getAccessToken() {
        access_token = given().queryParams(getTokenOAuthParams(auth_code))
                .log().all()
                .urlEncodingEnabled(false)
                .when()
                .post(Constants.URL_GOOGLE_TOKEN)
                .then().log().body()
                .extract().body().jsonPath().getString("access_token");
    }

    @Test
    public void getCourses() {
        given().queryParam("access_token", access_token)
                .when()
                .get(Constants.URL_RSA_GET_COURSE_PHP)
                .then().log().body();
    }
}
