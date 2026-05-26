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

public class InvoiceItemRatingStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildInvoiceItemRating(int id, int invoiceItemId, int ratingId) {
        Map<String, Object> rating = new LinkedHashMap<>();
        rating.put("id", id);
        rating.put("invoice_item_id", invoiceItemId);
        rating.put("rating_id", ratingId);
        return rating;
    }

    @Given("an invoice item rating exists with id {int}, invoice item id {int} and rating id {int}")
    public void anInvoiceItemRatingExists(int id, int invoiceItemId, int ratingId) throws Exception {
        Map<String, Object> rating = buildInvoiceItemRating(id, invoiceItemId, ratingId);
        mockMvc.perform(post("/invoiceItemRating/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rating)))
                .andExpect(status().isOk());
    }

    @When("I create an invoice item rating with id {int}, invoice item id {int} and rating id {int}")
    public void iCreateAnInvoiceItemRating(int id, int invoiceItemId, int ratingId) throws Exception {
        Map<String, Object> rating = buildInvoiceItemRating(id, invoiceItemId, ratingId);
        sharedState.setMvcResult(mockMvc.perform(post("/invoiceItemRating/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rating)))
                .andReturn());
    }

    @When("I get all invoice item ratings")
    public void iGetAllInvoiceItemRatings() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/invoiceItemRating")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get invoice item rating with id {int}")
    public void iGetInvoiceItemRatingWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/invoiceItemRating/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update invoice item rating with id {int} to invoice item id {int} and rating id {int}")
    public void iUpdateInvoiceItemRating(int id, int invoiceItemId, int ratingId) throws Exception {
        Map<String, Object> rating = buildInvoiceItemRating(id, invoiceItemId, ratingId);
        sharedState.setMvcResult(mockMvc.perform(put("/invoiceItemRating/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rating)))
                .andReturn());
    }

    @When("I delete invoice item rating with id {int}")
    public void iDeleteInvoiceItemRatingWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/invoiceItemRating/" + id))
                .andReturn());
    }

    @And("the response should contain invoice item rating with rating id {int}")
    public void theResponseShouldContainInvoiceItemRatingWithRatingId(int ratingId) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains("rating_id");
    }

    @And("the response should contain a list of invoice item ratings")
    public void theResponseShouldContainAListOfInvoiceItemRatings() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
