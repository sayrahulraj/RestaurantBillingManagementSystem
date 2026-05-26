Feature: Food Type Management
  As a restaurant manager
  I want to manage food type categories
  So that menu items can be organized by type

  Scenario: Create a new food type
    When I create a food type with id 1 and name "Main Course"
    Then the response status should be 200
    And the response should contain food type name "Main Course"

  Scenario: Get all food types
    Given a food type exists with id 1 and name "Starter"
    When I get all food types
    Then the response status should be 200
    And the response should contain a list of food types

  Scenario: Get food type by ID
    Given a food type exists with id 2 and name "Dessert"
    When I get food type with id 2
    Then the response status should be 200
    And the response should contain food type name "Dessert"

  Scenario: Update a food type
    Given a food type exists with id 3 and name "Beverage"
    When I update food type with id 3 to name "Beverages"
    Then the response status should be 200
    And the response should contain food type name "Beverages"

  Scenario: Delete a food type
    Given a food type exists with id 4 and name "Snacks"
    When I delete food type with id 4
    Then the response status should be 200
