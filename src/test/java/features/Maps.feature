@Maps

Feature: Validate Place APIs

  Scenario: Verify if the place is successfully added
    Given New place information is available
    When User calls "AddPlaceAPI" with Post call
    Then the API call responds with code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"