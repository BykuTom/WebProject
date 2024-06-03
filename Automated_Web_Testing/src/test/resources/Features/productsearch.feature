Feature: Product Search
  Scenario: The customer types a partial name of a product into the search bar and finds multiple products
    Given the customer is on the homepage
    When the customer searches for a "Radiant"
    And the customer clicks search
    Then products containing "Radiant" should be displayed in the search results

  Scenario: The customer types the exact name of a product into the search bar and finds the product
    Given the customer is on the homepage
    When the customer types "Radiant Tee" into the search bar
    And the customer clicks search
    Then the product "Radiant Tee" should be displayed in the search results

  Scenario: The customer searches for a non-existent item
    Given the customer is on the homepage
    When the customer types "football" into the search bar
    And the customer clicks search
    Then the customer should be redirected to the error page or receive an error message


