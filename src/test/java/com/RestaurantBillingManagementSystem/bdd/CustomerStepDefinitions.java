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

public class CustomerStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildCustomer(int id, String name, String phone) {
        Map<String, Object> customer = new LinkedHashMap<>();
        customer.put("id", id);
        customer.put("customer_name", name);
        customer.put("phone_no", phone);
        return customer;
    }

    @Given("a customer exists with id {int}, name {string} and phone {string}")
    public void aCustomerExistsWithIdNameAndPhone(int id, String name, String phone) throws Exception {
        Map<String, Object> customer = buildCustomer(id, name, phone);
        mockMvc.perform(post("/customer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());
    }

    @When("I create a customer with id {int}, name {string} and phone {string}")
    public void iCreateACustomerWithIdNameAndPhone(int id, String name, String phone) throws Exception {
        Map<String, Object> customer = buildCustomer(id, name, phone);
        sharedState.setMvcResult(mockMvc.perform(post("/customer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andReturn());
    }

    @When("I get all customers")
    public void iGetAllCustomers() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get customer with id {int}")
    public void iGetCustomerWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/customer/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update customer with id {int} to name {string} and phone {string}")
    public void iUpdateCustomerWithIdToNameAndPhone(int id, String name, String phone) throws Exception {
        Map<String, Object> customer = buildCustomer(id, name, phone);
        sharedState.setMvcResult(mockMvc.perform(put("/customer/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andReturn());
    }

    @When("I delete customer with id {int}")
    public void iDeleteCustomerWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/customer/" + id))
                .andReturn());
    }

    @And("the response should contain customer name {string}")
    public void theResponseShouldContainCustomerName(String name) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains(name);
    }

    @And("the response should contain a list of customers")
    public void theResponseShouldContainAListOfCustomers() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
