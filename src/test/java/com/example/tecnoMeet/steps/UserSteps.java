package com.example.tecnoMeet.steps;

import com.example.tecnoMeet.domain.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserSteps {

    private Exception error;
    private User user;
    private ResultActions response;

    @Autowired
    private MockMvc mockMvc;

    @When("I try to create a user with name {string} and email {string}")
    public void tryToCreateUser(String name, String email) {
        try {
            user = new User(name, email, "bio", "photo.jpg");
        } catch (Exception e) {
            error = e;
        }
    }

    @Then("the user should be created successfully")
    public void userShouldBeCreated() {
        assertNotNull(user);
        assertNull(error);
    }

    @Then("a user error should be thrown with message {string}")
    public void userErrorShouldBeThrown(String msg) {
        assertNotNull(error);
        assertEquals(msg, error.getMessage());
    }

    @When("I send a POST request for user to {string} with JSON:")
    public void postUserJson(String url, String json) throws Exception {
        response = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }

    @Then("the response code for user should be {int}")
    public void userStatusCode(int code) throws Exception {
        response.andExpect(MockMvcResultMatchers.status().isCreated()); // o is(201)

    }
}