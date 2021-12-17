package data;

public class Payload {
    public static String requestBody_add() {
        return "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String requestBody_updateWithNewAddress(){
        return "{\n" +
                "    \"place_id\": \"70102fbe26b563f16c2667b78a5c6ba7\",\n" +
                "    \"address\": \"1190 Sadashiv\",\n" +
                "    \"key\": \"qaclick123\"\n" +
                "}";
    }

    public static String response_courseDetails(){
        return "{\n" +
                "   \"dashboard\":{\n" +
                "      \"purchaseAmount\":910,\n" +
                "      \"website\":\"rahulshettyacademy.com\"\n" +
                "   },\n" +
                "   \"courses\":[\n" +
                "      {\n" +
                "         \"title\":\"Selenium Python\",\n" +
                "         \"price\":50,\n" +
                "         \"copies\":6\n" +
                "      },\n" +
                "      {\n" +
                "         \"title\":\"Cypress\",\n" +
                "         \"price\":40,\n" +
                "         \"copies\":4\n" +
                "      },\n" +
                "      {\n" +
                "         \"title\":\"RPA\",\n" +
                "         \"price\":45,\n" +
                "         \"copies\":10\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }

    public static String requestBody_addBook(){
        return "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\"bcd\",\n" +
                "\"aisle\":\"2926\",\n" +
                "\"author\":\"John foer\"\n" +
                "}";
    }
}
