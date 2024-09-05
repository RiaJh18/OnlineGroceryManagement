Feature: User Management

  Scenario: Get all users
    Given the API is running
    When I request all users
    Then I should receive a list of users

  Scenario: User registration
    Given the API is running
    When I register a new user with username "JohnDoe", password "password", and email "johndoe@example.com"
    Then I should receive a success message and the new user should be added

  Scenario: User login
    Given the API is running
    When I login with username "Neha" and password "pass1"
    Then I should receive a success message and the user details

  Scenario: Get all products
    Given the API is running
    When I request all products
    Then I should receive a list of products

  Scenario: Add a new product
    Given the API is running
    When I add a new product with name "Banana", category "Fruits", price 2, and stock quantity 80
    Then I should receive a success message and the new product should be added

  Scenario: Place an order
    Given the API is running
    When I place an order with user ID 1, product ID 1, quantity 2, and total price 6
    Then I should receive a success message and the new order should be added
