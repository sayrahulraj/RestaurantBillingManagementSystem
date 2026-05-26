Feature: Invoice Management
  As a restaurant manager
  I want to manage invoices
  So that I can track customer orders and billing

  Scenario: Create a new invoice
    When I create an invoice with id 1, order id "ORD-001", customer id 1 and charges id 1
    Then the response status should be 200
    And the response should contain order id "ORD-001"

  Scenario: Get all invoices
    Given an invoice exists with id 1, order id "ORD-002", customer id 1 and charges id 1
    When I get all invoices
    Then the response status should be 200
    And the response should contain a list of invoices

  Scenario: Get invoice by ID
    Given an invoice exists with id 2, order id "ORD-003", customer id 2 and charges id 1
    When I get invoice with id 2
    Then the response status should be 200
    And the response should contain order id "ORD-003"

  Scenario: Update an invoice
    Given an invoice exists with id 3, order id "ORD-004", customer id 1 and charges id 1
    When I update invoice with id 3 to order id "ORD-004-UPDATED" and customer id 2
    Then the response status should be 200
    And the response should contain order id "ORD-004-UPDATED"

  Scenario: Delete an invoice
    Given an invoice exists with id 4, order id "ORD-005", customer id 1 and charges id 1
    When I delete invoice with id 4
    Then the response status should be 200
