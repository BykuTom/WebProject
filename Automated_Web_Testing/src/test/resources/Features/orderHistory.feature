Feature: A user is attempting to to access their order history
  Scenario: As a signed in user I am trying to access my order page, having never ordered any items.
    Given I am a user who never ordered any items
    And I am on My Account page
    When I navigate to my orders page
    Then I see a message informing me that 'You have placed no orders

  Scenario: As a signed in user I am trying to access my order page, having previously placed an order.
    Given I am a user who previously ordered an item
    And I am on My Account page
    When I navigate to my orders page
    Then I see my previous orders

  Scenario: As an unsigned user I attempt to access my order page directly.
    When I try to access the page directly
    Then I am being redirected to the sign in page

  Scenario: As a signed in user I am attempting to access an order's detail.
    Given I am a user who previously ordered an item
    And I navigate to my orders page
    When I navigate to view first order
    Then I can see the order details and a valid order number