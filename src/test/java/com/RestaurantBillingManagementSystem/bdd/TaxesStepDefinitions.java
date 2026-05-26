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

public class TaxesStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildTaxes(int id, float cgst, float sgst, float convenienceFee) {
        Map<String, Object> taxes = new LinkedHashMap<>();
        taxes.put("id", id);
        taxes.put("cgst", cgst);
        taxes.put("sgst", sgst);
        taxes.put("convinience_fee", convenienceFee);
        return taxes;
    }

    @Given("taxes exist with id {int}, cgst {double}, sgst {double} and convenience fee {double}")
    public void taxesExistWithId(int id, double cgst, double sgst, double convenienceFee) throws Exception {
        Map<String, Object> taxes = buildTaxes(id, (float) cgst, (float) sgst, (float) convenienceFee);
        mockMvc.perform(post("/taxes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taxes)))
                .andExpect(status().isOk());
    }

    @When("I create taxes with id {int}, cgst {double}, sgst {double} and convenience fee {double}")
    public void iCreateTaxes(int id, double cgst, double sgst, double convenienceFee) throws Exception {
        Map<String, Object> taxes = buildTaxes(id, (float) cgst, (float) sgst, (float) convenienceFee);
        sharedState.setMvcResult(mockMvc.perform(post("/taxes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taxes)))
                .andReturn());
    }

    @When("I get all taxes")
    public void iGetAllTaxes() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/taxes")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get taxes with id {int}")
    public void iGetTaxesWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/taxes/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update taxes with id {int} to cgst {double}, sgst {double} and convenience fee {double}")
    public void iUpdateTaxesWithId(int id, double cgst, double sgst, double convenienceFee) throws Exception {
        Map<String, Object> taxes = buildTaxes(id, (float) cgst, (float) sgst, (float) convenienceFee);
        sharedState.setMvcResult(mockMvc.perform(put("/taxes/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taxes)))
                .andReturn());
    }

    @When("I delete taxes with id {int}")
    public void iDeleteTaxesWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/taxes/" + id))
                .andReturn());
    }

    @And("the response should contain taxes with cgst {double}")
    public void theResponseShouldContainTaxesWithCgst(double cgst) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains("cgst");
    }

    @And("the response should contain a list of taxes")
    public void theResponseShouldContainAListOfTaxes() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
