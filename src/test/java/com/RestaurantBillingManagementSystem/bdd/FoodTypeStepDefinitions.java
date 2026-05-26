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

public class FoodTypeStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildFoodType(int id, String name) {
        Map<String, Object> foodType = new LinkedHashMap<>();
        foodType.put("id", id);
        foodType.put("food_type_name", name);
        return foodType;
    }

    @Given("a food type exists with id {int} and name {string}")
    public void aFoodTypeExistsWithIdAndName(int id, String name) throws Exception {
        Map<String, Object> foodType = buildFoodType(id, name);
        mockMvc.perform(post("/foodType/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(foodType)))
                .andExpect(status().isOk());
    }

    @When("I create a food type with id {int} and name {string}")
    public void iCreateAFoodTypeWithIdAndName(int id, String name) throws Exception {
        Map<String, Object> foodType = buildFoodType(id, name);
        sharedState.setMvcResult(mockMvc.perform(post("/foodType/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(foodType)))
                .andReturn());
    }

    @When("I get all food types")
    public void iGetAllFoodTypes() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/foodType")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get food type with id {int}")
    public void iGetFoodTypeWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/foodType/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update food type with id {int} to name {string}")
    public void iUpdateFoodTypeWithIdToName(int id, String name) throws Exception {
        Map<String, Object> foodType = buildFoodType(id, name);
        sharedState.setMvcResult(mockMvc.perform(put("/foodType/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(foodType)))
                .andReturn());
    }

    @When("I delete food type with id {int}")
    public void iDeleteFoodTypeWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/foodType/" + id))
                .andReturn());
    }

    @And("the response should contain food type name {string}")
    public void theResponseShouldContainFoodTypeName(String name) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains(name);
    }

    @And("the response should contain a list of food types")
    public void theResponseShouldContainAListOfFoodTypes() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
