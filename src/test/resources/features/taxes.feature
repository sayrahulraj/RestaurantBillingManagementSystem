Feature: Taxes and Charges Management
  As a restaurant manager
  I want to manage tax configurations
  So that correct taxes are applied to invoices

  Scenario: Create a new tax configuration
    When I create taxes with id 1, cgst 2.5, sgst 2.5 and convenience fee 10.0
    Then the response status should be 200
    And the response should contain taxes with cgst 2.5

  Scenario: Get all tax configurations
    Given taxes exist with id 1, cgst 5.0, sgst 5.0 and convenience fee 15.0
    When I get all taxes
    Then the response status should be 200
    And the response should contain a list of taxes

  Scenario: Get tax configuration by ID
    Given taxes exist with id 2, cgst 9.0, sgst 9.0 and convenience fee 20.0
    When I get taxes with id 2
    Then the response status should be 200
    And the response should contain taxes with cgst 9.0

  Scenario: Update a tax configuration
    Given taxes exist with id 3, cgst 2.5, sgst 2.5 and convenience fee 10.0
    When I update taxes with id 3 to cgst 5.0, sgst 5.0 and convenience fee 12.0
    Then the response status should be 200
    And the response should contain taxes with cgst 5.0

  Scenario: Delete a tax configuration
    Given taxes exist with id 4, cgst 1.0, sgst 1.0 and convenience fee 5.0
    When I delete taxes with id 4
    Then the response status should be 200
