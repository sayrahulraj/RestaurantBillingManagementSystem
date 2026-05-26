package com.RestaurantBillingManagementSystem.bdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserReviewStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildUserReview(int id, int invoiceId, String comments, int ratingId) {
        Map<String, Object> review = new LinkedHashMap<>();
        review.put("id", id);
        review.put("invoice_id", invoiceId);
        review.put("comments", comments);
        review.put("rating_id", ratingId);
        return review;
    }

    @Given("a user review exists with id {int}, invoice id {int}, comments {string} and rating id {int}")
    public void aUserReviewExists(int id, int invoiceId, String comments, int ratingId) throws Exception {
        Map<String, Object> review = buildUserReview(id, invoiceId, comments, ratingId);
        mockMvc.perform(post("/userReview/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().isOk());
    }

    @When("I create a user review with id {int}, invoice id {int}, comments {string} and rating id {int}")
    public void iCreateAUserReview(int id, int invoiceId, String comments, int ratingId) throws Exception {
        Map<String, Object> review = buildUserReview(id, invoiceId, comments, ratingId);
        sharedState.setMvcResult(mockMvc.perform(post("/userReview/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andReturn());
    }

    @When("I get all user reviews")
    public void iGetAllUserReviews() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/userReview")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get user review with id {int}")
    public void iGetUserReviewWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/userReview/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update user review with id {int} to comments {string} and rating id {int}")
    public void iUpdateUserReview(int id, String comments, int ratingId) throws Exception {
        Map<String, Object> review = buildUserReview(id, 1, comments, ratingId);
        sharedState.setMvcResult(mockMvc.perform(put("/userReview/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andReturn());
    }

    @When("I delete user review with id {int}")
    public void iDeleteUserReviewWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/userReview/" + id))
                .andReturn());
    }

    @And("the response should contain user review with rating id {int}")
    public void theResponseShouldContainUserReviewWithRatingId(int ratingId) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains("rating_id");
    }

    @And("the response should contain a list of user reviews")
    public void theResponseShouldContainAListOfUserReviews() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
