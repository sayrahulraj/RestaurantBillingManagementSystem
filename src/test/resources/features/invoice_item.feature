Feature: Invoice Item Management
  As a restaurant manager
  I want to manage invoice items
  So that I can track individual items in each order

  Scenario: Create a new invoice item
    When I create an invoice item with id 1, invoice id 1, menu id 1 and quantity 2
    Then the response status should be 200
    And the response should contain invoice item with quantity 2

  Scenario: Get all invoice items
    Given an invoice item exists with id 1, invoice id 1, menu id 1 and quantity 3
    When I get all invoice items
    Then the response status should be 200
    And the response should contain a list of invoice items

  Scenario: Get invoice item by ID
    Given an invoice item exists with id 2, invoice id 1, menu id 2 and quantity 1
    When I get invoice item with id 2
    Then the response status should be 200
    And the response should contain invoice item with quantity 1

  Scenario: Update an invoice item
    Given an invoice item exists with id 3, invoice id 1, menu id 1 and quantity 2
    When I update invoice item with id 3 to menu id 3 and quantity 5
    Then the response status should be 200
    And the response should contain invoice item with quantity 5

  Scenario: Delete an invoice item
    Given an invoice item exists with id 4, invoice id 1, menu id 1 and quantity 1
    When I delete invoice item with id 4
    Then the response status should be 200
