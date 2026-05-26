Feature: Average Rating Management
  As a restaurant manager
  I want to track average dish ratings
  So that I can understand customer satisfaction per dish

  Scenario: Create a new average rating
    When I create an average rating with id 1, menu id 1, customer count 10 and avg rating 4.5
    Then the response status should be 200
    And the response should contain avg rating with menu id 1

  Scenario: Get all average ratings
    Given an average rating exists with id 1, menu id 1, customer count 5 and avg rating 3.8
    When I get all average ratings
    Then the response status should be 200
    And the response should contain a list of average ratings

  Scenario: Get average rating by ID
    Given an average rating exists with id 2, menu id 2, customer count 20 and avg rating 4.2
    When I get average rating with id 2
    Then the response status should be 200
    And the response should contain avg rating with menu id 2

  Scenario: Update an average rating
    Given an average rating exists with id 3, menu id 3, customer count 15 and avg rating 3.5
    When I update average rating with menu id 3 to customer count 20 and avg rating 4.0
    Then the response status should be 200
    And the response should contain avg rating with menu id 3

  Scenario: Delete an average rating
    Given an average rating exists with id 4, menu id 4, customer count 8 and avg rating 4.8
    When I delete average rating with id 4
    Then the response status should be 200
