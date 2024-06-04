Feature: As a user I need to be able to proceed through checkout
  Scenario: Enter valid credentials for all required shipping fields
    Given I have item(s) in my cart "push-it-messenger-bag"
    And I am on the checkout page
    When I enter valid UK credentials "chtest@email.com", "Christian", "Harborow", "27 Main Street", "Manchester", "07911 123456", "Fixed"
    And I click the next button
    Then the payment page is displayed
    And the entered details are displayed "Christian Harborow", "27 Main Street", "Manchester", "07911 123456"

  Scenario: Place order
    Given I am on the payment page
    When I click place order
    Then the success page is displayed
    And the order number is displayed

  Scenario: Required fields are left empty
    Given I have item(s) in my cart
    And I am on the checkout page
    When I click the next button
    Then "This is a required field." is displayed for all required fields

Scenario: Values with invalid formats are entered into fields
    Given I have item(s) in my cart
    And I am on the checkout page
    When I enter valid credentials
    Then an invalid format message is displayed for those fields