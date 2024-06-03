Feature: Product Search

  Scenario: The customer types the exact name of a product into the search bar and finds the product
    Given the customer is on the homepage
    When the customer types "Radiant Tee" into the search bar
    And clicks the search button
    Then the product "Radiant Tee" should be displayed in the search results

  Scenario: The customer types a partial product name into the search bar and finds the product
    Given the customer is on the homepage
    When the customer types "Radiant" into the search bar
    And clicks the search button
    Then the product "Radiant Tee" should be displayed in the search results

  Scenario: The customer types a query into the search bar that does not match any product in the database
    Given the customer is on the homepage
    When the customer types "Nonexistent Product Name" into the search bar
    And clicks the search button
    Then a message "No products found" should be displayed