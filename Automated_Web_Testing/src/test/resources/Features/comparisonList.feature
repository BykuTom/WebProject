Feature: As a user, I want to add items to my comparison list so that I can easily access a product I found at a different time
  Scenario Outline: Add item to comparison list, item appears
    Given the customer is on homepage
    When I click add to comparison list
    And I navigate to comparison list
    Then I should see the item in compare list <"Radiant Tee">
    Examples:
      |
      |