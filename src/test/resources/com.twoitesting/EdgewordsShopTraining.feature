Feature: Edgewords site shopping

  Background: User is logged into Victor's account
    Given I navigate to login page
    When I submit Victor's username and password
    Then I should be logged in

  Scenario: Buy a Beanie hat from shop and checkout
    Given I navigate to shop
    When I add a beanie hat to cart
    And checkout with coupon code
    Then Order should be placed and screenshot