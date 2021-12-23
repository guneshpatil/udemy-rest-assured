@Maps

Feature: Validate Place APIs

  Scenario Outline: Verify if the place is successfully added
    Given New place information is available
    And "name" is "<name>"
    And "address" is "<address>"
    And "language" is "<language>"
    When User calls "AddPlaceUrl" with Post call
    Then the API call responds with code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"

    Examples:
      | name             | address    | language
      | Wellington House | 398 Farmington Avenue | French
      | Nova House | 76 Washington Avenue | Swedish