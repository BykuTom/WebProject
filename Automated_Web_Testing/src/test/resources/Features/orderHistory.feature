Feature: A user is attempting to to access their order history
  Scenario: As a signed in user I am trying to access my order page, having never ordered any items.
    Given I am a user who never ordered any items
    And I am on My Account page
    When I navigate to my orders page
    Then I see a message informing me that 'You have placed no orders

#  Scenario : As a signed in user I am trying to access my order page, having previously placed an order.
#    Given I am on My Account page
#    When I navigate to my orders page
#    Then I see my previous orders
#    And I see the correct <order_number>

  Scenario: As an unsigned user I attempt to access my order page directly.
    When I try to access the page directly
    Then I am being redirected to the sign in page

#  Scenario: As a signed in user I am attempting to access an order's detail.
#    Given I am on My Orders page
#    When I navigate to view order
#    Then I can see the details of said order, including <order_number>, ,
#    And I can Print Order