Feature: Product Page Navigation

  Scenario: Customer navigates to the product page of a specific item
    Given the customer is on the homepage
    When the customer searches for a "specific item"
    And the customer clicks on the product link for a "specific item"
    Then the customer should be redirected to the product page for "specific item"
    And the product page should display the item details, price, and add to cart button
