Feature: Product Info

  Scenario: Customer searches for specific product type to get info
    Given the customer is on the homepage
    When the customer types "Radiant Tee" into the search bar
    And clicks the search button
    Then the customer should be redirected to the product page for "Radiant Tee"
    Then the product page should display the item details, price, and add to cart button

  Scenario: Customer searches for specific product to see reviews
    Given the customer is on the homepage
    When the customer types "Jackets" into the search bar
    And clicks the search button
    Then the customer should be redirected to the product page for "Jackets"
    Then the product page should display the reviews section

  Scenario: Customer searches for an out-of-stock or non-existent item
    Given the customer is on the homepage
    When the customer types "football" into the search bar
    And clicks the search button
    Then the customer should be redirected to the product page for "football"
    Then the customer should be redirected to the error page or receive an error message


