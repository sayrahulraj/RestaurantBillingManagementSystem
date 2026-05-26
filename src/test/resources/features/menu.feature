Feature: Menu Management
  As a restaurant manager
  I want to manage the restaurant menu
  So that customers can see available dishes

  Scenario: Create a new menu item
    When I create a menu item with id 1, dish name "Butter Chicken", food type id 1, price 350.0, veg false, available true
    Then the response status should be 200
    And the response should contain dish name "Butter Chicken"

  Scenario: Get all menu items
    Given a menu item exists with id 1, dish name "Paneer Tikka", food type id 1, price 250.0, veg true, available true
    When I get all menu items
    Then the response status should be 200
    And the response should contain a list of menu items

  Scenario: Get menu item by ID
    Given a menu item exists with id 2, dish name "Dal Makhani", food type id 1, price 200.0, veg true, available true
    When I get menu item with id 2
    Then the response status should be 200
    And the response should contain dish name "Dal Makhani"

  Scenario: Update a menu item
    Given a menu item exists with id 3, dish name "Naan", food type id 2, price 50.0, veg true, available true
    When I update menu item with id 3 to dish name "Garlic Naan" and price 70.0
    Then the response status should be 200
    And the response should contain dish name "Garlic Naan"

  Scenario: Delete a menu item
    Given a menu item exists with id 4, dish name "Roti", food type id 2, price 30.0, veg true, available true
    When I delete menu item with id 4
    Then the response status should be 200
