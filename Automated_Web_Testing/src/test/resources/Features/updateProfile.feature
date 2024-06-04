Feature: As a user, I want to be able to change my profile information so that my purchases
  are delivered to the right address.
  Scenario Outline: Enter valid credentials, successfully sign up
    Given I am on the my account page
    When I click Edit Address
    And I enter a new address "<street>"
    Then The address is updated to "<street>"
    Examples:
      |street|
      |165 Borough High Street|
      |32 Timsbury Walk       |

