package data;

public class Payload {
    public static String requestBody_add() {
        return """
                {
                    "location": {
                        "lat": -38.383494,
                        "lng": 33.427362
                    },
                    "accuracy": 50,
                    "name": "Frontline house",
                    "phone_number": "(+91) 983 893 3937",
                    "address": "29, side layout, cohen 09",
                    "types": [
                        "shoe park",
                        "shop"
                    ],
                    "website": "http://google.com",
                    "language": "French-IN"
                }""";
    }

    public static String requestBody_updateWithNewAddress(){
        return """
                {
                    "place_id": "70102fbe26b563f16c2667b78a5c6ba7",
                    "address": "1190 Sadashiv",
                    "key": "qaclick123"
                }""";
    }

    public static String response_courseDetails(){
        return """
                {
                   "dashboard":{
                      "purchaseAmount":910,
                      "website":"rahulshettyacademy.com"
                   },
                   "courses":[
                      {
                         "title":"Selenium Python",
                         "price":50,
                         "copies":6
                      },
                      {
                         "title":"Cypress",
                         "price":40,
                         "copies":4
                      },
                      {
                         "title":"RPA",
                         "price":45,
                         "copies":10
                      }
                   ]
                }""";
    }

    public static String requestBody_addBook(){
        return """
                {
                "name":"Learn Appium Automation with Java",
                "isbn":"tret",
                "aisle":"292326",
                "author":"John foer"
                }""";
    }
}
