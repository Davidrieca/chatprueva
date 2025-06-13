package com.example.tecnoMeet.steps;

import com.example.tecnoMeet.domain.Match;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MatchSteps {

    private Match match;
    private Exception thrownError;
    private ResultActions response;

    @Autowired
    private MockMvc mockMvc;

    @When("I try to match {string} with {string}")
    public void i_try_to_match_with(String user1, String user2) {
        try {
            match = new Match(user1, user2);
        } catch (Exception e) {
            thrownError = e;
        }
    }

    @Then("the match should be created with status {string}")
    public void matchShouldBeCreated(String expectedStatus) {
        assertNotNull(match);
        assertEquals(expectedStatus, match.getStatus().name());
    }

    @Then("a match error should be thrown with message {string}")
    public void matchErrorShouldBeThrown(String expectedMessage) {
        assertNotNull(thrownError);
        assertEquals(expectedMessage, thrownError.getMessage());
    }

    @When("I send a POST request for match to {string}")
    public void i_send_a_post_request_for_match_to(String url) throws Exception {
        response = mockMvc.perform(post(url));
    }

    @When("I send a DELETE request for match to {string}")
    public void i_send_a_delete_request_for_match_to(String url) throws Exception {
        response = mockMvc.perform(delete(url));
    }

    @Then("the response code for match should be {int}")
    public void the_response_code_for_match_should_be(Integer expectedStatus) throws Exception {
        response.andExpect(status().is(expectedStatus));
    }

}
