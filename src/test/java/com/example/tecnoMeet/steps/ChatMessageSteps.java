package com.example.tecnoMeet.steps;

import com.example.tecnoMeet.domain.ChatMessage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ChatMessageSteps {

    private ChatMessage message;
    private Exception error;
    private ResultActions response;

    @Autowired
    private MockMvc mockMvc;

    @When("I try to create a message with matchId {string}, senderId {string} and content {string}")
    public void tryToCreateMessage(String matchId, String senderId, String content) {
        try {
            message = new ChatMessage(matchId, senderId, content);
        } catch (Exception e) {
            error = e;
        }
    }

    @Then("the message should be created successfully")
    public void messageShouldBeCreated() {
        assertNotNull(message);
        assertNull(error);
    }

    @Then("a message error should be thrown with message {string}")
    public void messageErrorShouldBeThrown(String msg) {
        assertNotNull(error);
        assertEquals(msg, error.getMessage());
    }

    @When("I send a POST request for message to {string}")
    public void postMessage(String pathWithQuery) throws Exception {
        URI uri = new URI(pathWithQuery);
        String[] pathParts = uri.getPath().split("/");
        String matchId = pathParts[pathParts.length - 1];

        // Set up dummy users and a match for the request
        Map<String, String> queryParamsTmp = Arrays.stream(uri.getQuery().split("&"))
                .map(p -> p.split("="))
                .collect(Collectors.toMap(p -> p[0], p -> p[1]));
        String sender = queryParamsTmp.get("senderId");
        setupChatMatch(matchId, sender, "receiver");

        // Extraer query params
        Map<String, String> queryParams = Arrays.stream(uri.getQuery().split("&"))
                .map(p -> p.split("="))
                .collect(Collectors.toMap(p -> p[0], p -> p[1]));

        response = mockMvc.perform(
                post("/chats/" + matchId)
                        .contentType("application/x-www-form-urlencoded")
                        .param("senderId", queryParams.get("senderId"))
                        .param("content", queryParams.get("content"))
        );
    }


    @Autowired
    private JdbcClient jdbcClient;

    @Given("a chat match with ID {string} exists between users {string} and {string}")
    public void setupChatMatch(String matchId, String user1, String user2) {
        jdbcClient.sql("INSERT INTO USERS (id, name) VALUES (:id, :name)")
                .param("id", user1).param("name", user1).update();
        jdbcClient.sql("INSERT INTO USERS (id, name) VALUES (:id, :name)")
                .param("id", user2).param("name", user2).update();

        jdbcClient.sql("INSERT INTO MATCHES (id, liker_id, liked_id, status) VALUES (:id, :liker, :liked, 'MATCHED')")
                .param("id", matchId)
                .param("liker", user1)
                .param("liked", user2)
                .update();
    }


    @Then("the response code for message should be {int}")
    public void messageStatusCode(int code) throws Exception {
        response.andExpect(MockMvcResultMatchers.status().is(code));
    }
    @Then("an error should be thrown with message {string}")
    public void aMessageErrorShouldBeThrown(String message) {
        assertNotNull(error);
        assertEquals(message, error.getMessage());
    }

}