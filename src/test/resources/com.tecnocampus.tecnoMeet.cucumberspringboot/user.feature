Feature: User domain and REST testing

  Scenario: Create user with valid data
    When I try to create a user with name "Alice" and email "alice@tecnocampus.cat"
    Then the user should be created successfully

  Scenario: Create user with invalid email
    When I try to create a user with name "Bob" and email ""
    Then a user error should be thrown with message "Email cannot be empty"


  Scenario: Send a user via POST
    When I send a POST request for user to "/users" with JSON:
    """
    {
      "name": "Charlie",
      "email": "charlie@tecnocampus.cat",
      "bio": "Hi there!",
      "photoUrl": "photo.png"
    }
    """
    Then the response code for user should be 200

