Feature: Filter products by category

  Scenario: The customer filters products by selecting a single category
    Given the customer is on the homepage
    When the customer navigates to the "Men" category
    Then the products in the "Men" category should be displayed

  Scenario: The customer filters products by selecting a subcategory within a main category
    Given the customer is on the homepage
    When the customer selects the "Tops" subcategory under "Men"
    Then the products in the "Tops" subcategory under "Men" should be displayed

  Scenario: The customer filters products by selecting a category that currently has no products
    Given the customer is on the homepage
    When the customer selects the "Training" subcategory under "Video"
    Then a message indicating "We can't find products matching the selection." should be displayed
