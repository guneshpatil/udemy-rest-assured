package api.map;

import data.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonParsing {
    String response;
    JsonPath jsonPath;

    @BeforeSuite
    public void setup() {
        response = Payload.response_courseDetails();
        jsonPath = new JsonPath(response);
    }

    @Test
    public void printCourseDetails() {
        System.out.println(jsonPath.getInt("courses.size()"));
        System.out.println(jsonPath.getInt("dashboard.purchaseAmount"));
        System.out.println(jsonPath.getString("courses[0].title"));
        for (int i = 0; i < jsonPath.getInt("courses.size()"); i++) {
            System.out.print(jsonPath.getString("courses[" + i + "].title") + " -- ");
            System.out.println(jsonPath.getString("courses[" + i + "].price"));
        }
    }

    @Test
    public void checkTotalPrice() {
        List<Integer> prices = new ArrayList<>(Helper.getCollection(response, "courses.price"));
        System.out.println(prices.stream().reduce(0, Integer::sum));
        System.out.println(Helper.getCollection(response, "courses.findAll{it.title=='RPA'}.copies"));
        List<Integer> copies = new ArrayList<>(Helper.getCollection(response, "courses.copies"));
        int i = 0, sum = 0;
        for (int price : prices) {
            sum += price * copies.get(i++);
        }
        Assert.assertEquals(sum, (int) Helper.getField(response, "dashboard.purchaseAmount"), "Purchase amount isn't same as the sum!");

    }

}
