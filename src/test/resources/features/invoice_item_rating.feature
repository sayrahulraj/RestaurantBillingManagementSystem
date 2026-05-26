Feature: Invoice Item Rating Management
  As a restaurant manager
  I want to manage ratings for invoice items
  So that I can track customer feedback on individual items

  Scenario: Create a new invoice item rating
    When I create an invoice item rating with id 1, invoice item id 1 and rating id 5
    Then the response status should be 200
    And the response should contain invoice item rating with rating id 5

  Scenario: Get all invoice item ratings
    Given an invoice item rating exists with id 1, invoice item id 1 and rating id 4
    When I get all invoice item ratings
    Then the response status should be 200
    And the response should contain a list of invoice item ratings

  Scenario: Get invoice item rating by ID
    Given an invoice item rating exists with id 2, invoice item id 2 and rating id 3
    When I get invoice item rating with id 2
    Then the response status should be 200
    And the response should contain invoice item rating with rating id 3

  Scenario: Update an invoice item rating
    Given an invoice item rating exists with id 3, invoice item id 3 and rating id 2
    When I update invoice item rating with id 3 to invoice item id 3 and rating id 4
    Then the response status should be 200
    And the response should contain invoice item rating with rating id 4

  Scenario: Delete an invoice item rating
    Given an invoice item rating exists with id 4, invoice item id 4 and rating id 5
    When I delete invoice item rating with id 4
    Then the response status should be 200
