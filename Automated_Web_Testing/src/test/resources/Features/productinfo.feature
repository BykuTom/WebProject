Feature: Product Info

  Scenario: Customer searches for specific product type to get info
    Given the customer is on the homepage
    When the customer types "Radiant Tee" into the search bar
    And clicks the search button
    Then the customer should be redirected to the product page for "Radiant Tee"
    Then the product page should display the item details, price, and add to cart button

  Scenario: Customer navigates to the page of a specific item using link
    Given the customer is on the homepage
    When the customer clicks on the product link for a "jacket"
    Then the customer should be redirected to the individual product page for "jacket"
    Then the product page should display the item details, price, and add to cart button

  Scenario: Customer searches for an out-of-stock or non-existent item
    Given the customer is on the homepage
    When the customer searches for an out-of-stock or non-existent item
    And the customer clicks search
    Then the customer should be redirected to the error page or receive an error message


