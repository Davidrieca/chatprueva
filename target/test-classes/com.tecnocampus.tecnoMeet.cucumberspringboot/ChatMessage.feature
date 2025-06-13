Feature: Chat message domain and REST testing

  Scenario: Create a message with valid data
    When I try to create a message with matchId "m1", senderId "u1" and content "Hello!"
    Then the message should be created successfully

  Scenario: Create a message with empty content
    When I try to create a message with matchId "m1", senderId "u1" and content ""
    Then an error should be thrown with message "Message content required"

  Scenario: Send a chat message via POST
    When I send a POST request for message to "/chats/m1?senderId=u1&content=Hello"
    Then the response code for message should be 200
