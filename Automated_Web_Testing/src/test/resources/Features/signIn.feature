Feature: As a registered user,
  I want to be able to log into the website,
  So that I can purchase and browse items.
  Scenario Outline: Enter the correct details
    Given I am on the sign-in page
    And I have entered an email "<email>"
    And I have entered a password "<password>"
    When I click Sign in
    Then I should be redirected to my dashboard
    Examples:
      | email                   | password              |
      | sdiep@spartaglobal.com  | Bv6_ELcSJ#dhX53       |
      | BigChungus@nish.com     | BigChungusAmongUs23.  |
  Scenario Outline: Enter incorrect details
  Given I am on the sign-in page
  And I have entered an email "<email>"
  And I have entered a password "<password>"
  When I click Sign in
  Then I should see an alert containing the error message
    Examples:
      | email                     | password  |
      | sdiep@spartaglobal.com    | abc123    |
      | steven_diep@hotmail.co.uk | abc123    |