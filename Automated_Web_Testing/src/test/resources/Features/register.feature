Feature: As a new user of an e-commerce website,
  I want to be able to register an account,
  So that I can browse and purchase items.
  Scenario Outline: Enter valid credentials, successfully sign up
    Given I am on the sign-up page
    And I have entered my first name "<firstName>"
    And I have entered my last name "<lastName>"
    And I have entered an email "<email>"
    And I have entered a password "<password>"
    When I click Create an Account
    Then I should be redirected to my profile dashboard and see an alert Thank you for registering with Main Website Store
    Examples:
    # Note: Details cannot be deleted to reproduce tests later
      | firstName | lastName | email                  | password        |
      | Steven    | Diep     | sdiep@sportaglobal.com | Bv6_ELcSJ#dhX53 |

  Scenario Outline: Enter invalid credentials, prompt for required fields is shown
    Given I am on the sign-up page
    And I have entered my first name "<firstName>"
    And I have entered my last name "<lastName>"
    And I have entered an email "<email>"
    And I have entered a password "<password>"
    When I click Create an Account
    Then I should see an alert containing the error This is a required field
    Examples:
      | firstName | lastName | email        | password        |
      |           |  diep    | sd@gmail.com | Bv6_ELcSJ#dhX53 |
      |  STEVEN   |          | sd@gmail.com | Bv6_ELcSJ#dhX53 |
      |  STEVEN   |  diep    |              | Bv6_ELcSJ#dhX53 |
      |  STEVEN   |  diep    | sd@gmail.com |                 |
  Scenario Outline: Enter incorrect validation password
    Given I am on the sign-up page
    And I have entered my first name "<firstName>"
    And I have entered my last name "<lastName>"
    And I have entered an email "<email>"
    And I have entered a password "<password>"
    When I click Create an Account
    Then I should see an alert containing the error message Minimum length of this field must be equal or greater than 8 symbols
    Examples:
    # Note: Check 7, 6, 4 character password
      | firstName | lastName | email        | password  |
      | STEVEN    |  diep    | sd@gmail.com | abc1234   |
      | STEVEN    |  diep    | sd@gmail.com | qwerty    |
      | STEVEN    |  diep    | sd@gmail.com | 1234      |
  Scenario Outline: Enter incorrect format email
    Given I am on the sign-up page
    And I have entered my first name "<firstName>"
    And I have entered my last name "<lastName>"
    And I have entered an email "<email>"
    And I have entered a password "<password>"
    When I click Create an Account
    Then I should see an alert containing the error message
    Examples:
      | firstName | lastName | email        | password  |
      | STEVEN    |  diep    | sdp@gmail    | abc1234   |
      | STEVEN    |  diep    | qwerty       | qwerty    |
      | STEVEN    |  diep    | @hotmail.com | 1234      |