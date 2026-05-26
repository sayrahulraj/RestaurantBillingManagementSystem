Feature: Payment Management
  As a restaurant manager
  I want to manage payments
  So that I can track customer payments for their orders

  Scenario: Create a new payment
    When I create a payment with id 1, invoice id 1, customer id 1, payment type id 1 and total amount 500.0
    Then the response status should be 200
    And the response should contain payment with total amount 500.0

  Scenario: Get all payments
    Given a payment exists with id 1, invoice id 1, customer id 1, payment type id 1 and total amount 300.0
    When I get all payments
    Then the response status should be 200
    And the response should contain a list of payments

  Scenario: Get payment by ID
    Given a payment exists with id 2, invoice id 1, customer id 2, payment type id 2 and total amount 750.0
    When I get payment with id 2
    Then the response status should be 200
    And the response should contain payment with total amount 750.0

  Scenario: Update a payment
    Given a payment exists with id 3, invoice id 1, customer id 1, payment type id 1 and total amount 400.0
    When I update payment with id 3 to payment type id 2 and total amount 450.0
    Then the response status should be 200
    And the response should contain payment with total amount 450.0

  Scenario: Delete a payment
    Given a payment exists with id 4, invoice id 1, customer id 1, payment type id 1 and total amount 200.0
    When I delete payment with id 4
    Then the response status should be 200
