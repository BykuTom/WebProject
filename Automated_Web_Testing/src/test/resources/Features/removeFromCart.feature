Feature: As a userI need to be able to remove items from my cart

  Scenario: Remove the only item from the cart
    Given There is a single item "push-it-messenger-bag" in the cart
    And I am on the cart page
    When I click the delete item button for "Push It Messenger Bag"
    Then You have no items in your shopping cart is displayed

  Scenario: Remove an item from a cart with multiple items
    Given There are multiple items in the cart "push-it-messenger-bag, driven-backpack"
    And I am on the cart page
    When I click the delete item button for "Push It Messenger Bag"
    Then the cart count is set to 1
    And the item "Push It Messenger Bag" is removed

  Scenario: Reduce the quantity of an item within the cart
    Given There is an item "push-it-messenger-bag" in the cart with quantity 3
    And I am on the cart page
    When the quantity value of "Push It Messenger Bag" is overwritten with 1
    And the Update Shopping Cart button is clicked
    Then the cart count is set to 1