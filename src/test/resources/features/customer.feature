Feature: Customer Management
  As a restaurant manager
  I want to manage customer records
  So that I can track customer information for billing

  Scenario: Create a new customer
    When I create a customer with id 1, name "John Doe" and phone "9876543210"
    Then the response status should be 200
    And the response should contain customer name "John Doe"

  Scenario: Get all customers
    Given a customer exists with id 1, name "Alice" and phone "1111111111"
    When I get all customers
    Then the response status should be 200
    And the response should contain a list of customers

  Scenario: Get customer by ID
    Given a customer exists with id 2, name "Bob" and phone "2222222222"
    When I get customer with id 2
    Then the response status should be 200
    And the response should contain customer name "Bob"

  Scenario: Update a customer
    Given a customer exists with id 3, name "Charlie" and phone "3333333333"
    When I update customer with id 3 to name "Charlie Updated" and phone "3333334444"
    Then the response status should be 200
    And the response should contain customer name "Charlie Updated"

  Scenario: Delete a customer
    Given a customer exists with id 4, name "Dave" and phone "4444444444"
    When I delete customer with id 4
    Then the response status should be 200
