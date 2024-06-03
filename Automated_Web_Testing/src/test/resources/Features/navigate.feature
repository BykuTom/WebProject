Feature: As a user I want to be able to navigate around magento.softwaretestingboard.com website.
  Scenario: Navigating to Create an Account page
    Given I am on the home page
    When I click on 'Create an Account' link
    Then I am taken to an account creation page

  Scenario: Navigating to Login page
    Given I am on the home page
    When I click on 'Sign In' link
    Then I am taken to Customer login page

  Scenario: Navigating to Sale page
    Given I am on the home page
    When I click on 'Sale' link
    Then I am taken to the Sale page
#
  Scenario: Navigating to Jackets page
    Given I am on the home page
    And I hover over 'Men' link
    And I move to hover over 'Tops' link
    When I click the 'Jackets' link
    Then I am taken to the Jackets page
#
  Scenario: Navigating to Basket
    Given I am on the home page
    And There are items inside the basket
    When I click on the basket icon link
    And I click on the 'View and Edit Cart' link
    Then I am taken to the basket page

#  Scenario: Navigating to individual item from Men's short's page
#    Given I am on the Men's short's page
#    When I click on an individual shop item
#    Then I am taken to the individual item's page
#
#  Scenario: Navigating directly to an individual items
#    Given I have a link to an individual item
#    When I enter the link into my browser search
#    Then I am taken to the individual item's page