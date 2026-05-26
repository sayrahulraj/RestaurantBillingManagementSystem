Feature: User Review Management
  As a restaurant manager
  I want to manage user reviews
  So that I can track customer feedback on their dining experience

  Scenario: Create a new user review
    When I create a user review with id 1, invoice id 1, comments "Great food!" and rating id 5
    Then the response status should be 200
    And the response should contain user review with rating id 5

  Scenario: Get all user reviews
    Given a user review exists with id 1, invoice id 1, comments "Excellent service" and rating id 4
    When I get all user reviews
    Then the response status should be 200
    And the response should contain a list of user reviews

  Scenario: Get user review by ID
    Given a user review exists with id 2, invoice id 2, comments "Average experience" and rating id 3
    When I get user review with id 2
    Then the response status should be 200
    And the response should contain user review with rating id 3

  Scenario: Update a user review
    Given a user review exists with id 3, invoice id 1, comments "Good" and rating id 3
    When I update user review with id 3 to comments "Very Good" and rating id 4
    Then the response status should be 200
    And the response should contain user review with rating id 4

  Scenario: Delete a user review
    Given a user review exists with id 4, invoice id 1, comments "Not bad" and rating id 3
    When I delete user review with id 4
    Then the response status should be 200
