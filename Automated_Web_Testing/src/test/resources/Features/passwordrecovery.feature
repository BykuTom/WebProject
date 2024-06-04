Feature: As a user who forgot their password,
  I want to be able to reset my password using my email address,
  So I can regain access to my account.
  Scenario Outline: Enter email for account recovery
  Given I am on the forgot password page
  And I have entered an email to reset password "<email>"
  When I click Reset My Password
  Then I should see an alert If there is an account associated with email
    Examples:
      | email                  |
      | sdiep@spartaglobal.com |