@smoketest
Feature: Verify checkout function in Green Kart Website

  Scenario: Successfully add max and min price Item to cart and check out
    Given user go to Green Kart home page
    And user verifies page title as "GreenKart - veg and fruits kart"
    When user capture all prices from descending order
    Then user adds the max and min price item to cart and capture total price
    And user clicks on cart button then proceed to checkout
    Then user verifies total price previous page
    And user click on place order
    Then user chooses country as "United States"
    And user clicks on check box for terms and conditions
    And user clicks on proceed button
    Then user verify "Thank you, your order has been placed successfully" message
