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

public class MenuStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildMenu(int id, String dishName, int foodTypeId, float price, boolean veg, boolean available) {
        Map<String, Object> menu = new LinkedHashMap<>();
        menu.put("id", id);
        menu.put("dish_name", dishName);
        menu.put("food_type_id", foodTypeId);
        menu.put("price", price);
        menu.put("is_veg", veg);
        menu.put("is_available", available);
        return menu;
    }

    @Given("a menu item exists with id {int}, dish name {string}, food type id {int}, price {double}, veg {word}, available {word}")
    public void aMenuItemExists(int id, String dishName, int foodTypeId, double price, String veg, String available) throws Exception {
        Map<String, Object> menu = buildMenu(id, dishName, foodTypeId, (float) price, Boolean.parseBoolean(veg), Boolean.parseBoolean(available));
        mockMvc.perform(post("/menu/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menu)))
                .andExpect(status().isOk());
    }

    @When("I create a menu item with id {int}, dish name {string}, food type id {int}, price {double}, veg {word}, available {word}")
    public void iCreateAMenuItem(int id, String dishName, int foodTypeId, double price, String veg, String available) throws Exception {
        Map<String, Object> menu = buildMenu(id, dishName, foodTypeId, (float) price, Boolean.parseBoolean(veg), Boolean.parseBoolean(available));
        sharedState.setMvcResult(mockMvc.perform(post("/menu/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menu)))
                .andReturn());
    }

    @When("I get all menu items")
    public void iGetAllMenuItems() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/menu")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get menu item with id {int}")
    public void iGetMenuItemWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/menu/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update menu item with id {int} to dish name {string} and price {double}")
    public void iUpdateMenuItemWithIdToDishNameAndPrice(int id, String dishName, double price) throws Exception {
        Map<String, Object> menu = new LinkedHashMap<>();
        menu.put("id", id);
        menu.put("dish_name", dishName);
        menu.put("food_type_id", 1);
        menu.put("price", (float) price);
        menu.put("is_veg", true);
        menu.put("is_available", true);
        sharedState.setMvcResult(mockMvc.perform(put("/menu/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menu)))
                .andReturn());
    }

    @When("I delete menu item with id {int}")
    public void iDeleteMenuItemWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/menu/" + id))
                .andReturn());
    }

    @And("the response should contain dish name {string}")
    public void theResponseShouldContainDishName(String dishName) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains(dishName);
    }

    @And("the response should contain a list of menu items")
    public void theResponseShouldContainAListOfMenuItems() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
