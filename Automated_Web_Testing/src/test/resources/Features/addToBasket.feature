Feature: As a user I need to be able to add items to the cart
  As a user
  I need to be able to add items to my cart
  So that I can purchase items at checkout

  Scenario: Add single item to cart
    Given I am on a product page
    When I click add to cart
    Then the cart count is incremented to 1
    And the item appears on the cart page

  Scenario: Add a quantity of the same item to cart
    Given I am on a product page
    When I set the products quantity to 3
    And I click add to cart
    Then the cart count is incremented to 3
    And the item appears on the cart page
    And the item's quantity attribute is 3

  Scenario: Add an item that is already present in the cart
    Given I am on a product page
    When I click add to cart twice
    Then the cart count is incremented to 2
    And the item appears on the cart page
    And the item's quantity attribute is 2

  Scenario: Add two different items to the cart
    Given I am on a product page
    When I click add to cart
    And I go a different product page
    And I click add to cart
    Then the cart count is incremented to 2
    And both items are displayed separately in the cart

  Scenario: Add an additional variant of an already present item to the cart
    Given I am on a variant product page
    When I set size to XS
    And I set colour to Black
    And I click add to cart
    And I set size to L
    And I click add to cart
    Then the cart count is incremented to 2
    And both variants are displayed separately in the cart