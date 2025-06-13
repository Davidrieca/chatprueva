Feature: Match domain and REST testing

  Scenario: Create a match between two users
    When I try to match "user1" with "user2"
    Then the match should be created with status "PENDING"

  Scenario: Send a match via POST
    When I send a POST request for match to "/matches/swipe/user1/user2"
    Then the response code for match should be 201

  Scenario: Delete a match
    When I send a DELETE request for match to "/matches/m1"
    Then the response code for match should be 200
