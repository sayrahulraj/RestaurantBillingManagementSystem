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

public class AvgRatingStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildAvgRating(int id, int menuId, int customerCount, float avgRating) {
        Map<String, Object> rating = new LinkedHashMap<>();
        rating.put("id", id);
        rating.put("menu_id", menuId);
        rating.put("customercount", customerCount);
        rating.put("avg_rating", avgRating);
        return rating;
    }

    @Given("an average rating exists with id {int}, menu id {int}, customer count {int} and avg rating {double}")
    public void anAvgRatingExists(int id, int menuId, int customerCount, double avgRating) throws Exception {
        Map<String, Object> rating = buildAvgRating(id, menuId, customerCount, (float) avgRating);
        mockMvc.perform(post("/avgRating/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rating)))
                .andExpect(status().isOk());
    }

    @When("I create an average rating with id {int}, menu id {int}, customer count {int} and avg rating {double}")
    public void iCreateAnAvgRating(int id, int menuId, int customerCount, double avgRating) throws Exception {
        Map<String, Object> rating = buildAvgRating(id, menuId, customerCount, (float) avgRating);
        sharedState.setMvcResult(mockMvc.perform(post("/avgRating/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rating)))
                .andReturn());
    }

    @When("I get all average ratings")
    public void iGetAllAvgRatings() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/avgRating")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get average rating with id {int}")
    public void iGetAvgRatingWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/avgRating/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update average rating with menu id {int} to customer count {int} and avg rating {double}")
    public void iUpdateAvgRating(int menuId, int customerCount, double avgRating) throws Exception {
        Map<String, Object> rating = buildAvgRating(1, menuId, customerCount, (float) avgRating);
        sharedState.setMvcResult(mockMvc.perform(put("/avgRating/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rating)))
                .andReturn());
    }

    @When("I delete average rating with id {int}")
    public void iDeleteAvgRatingWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/avgRating/" + id))
                .andReturn());
    }

    @And("the response should contain avg rating with menu id {int}")
    public void theResponseShouldContainAvgRatingWithMenuId(int menuId) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains("menu_id");
    }

    @And("the response should contain a list of average ratings")
    public void theResponseShouldContainAListOfAvgRatings() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
