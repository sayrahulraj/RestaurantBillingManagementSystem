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

public class InvoiceItemStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildInvoiceItem(int id, int invoiceId, int menuId, int quantity) {
        Map<String, Object> invoiceItem = new LinkedHashMap<>();
        invoiceItem.put("id", id);
        invoiceItem.put("invoice_id", invoiceId);
        invoiceItem.put("menu_id", menuId);
        invoiceItem.put("quantity", quantity);
        return invoiceItem;
    }

    @Given("an invoice item exists with id {int}, invoice id {int}, menu id {int} and quantity {int}")
    public void anInvoiceItemExists(int id, int invoiceId, int menuId, int quantity) throws Exception {
        Map<String, Object> invoiceItem = buildInvoiceItem(id, invoiceId, menuId, quantity);
        mockMvc.perform(post("/invoiceItem/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceItem)))
                .andExpect(status().isOk());
    }

    @When("I create an invoice item with id {int}, invoice id {int}, menu id {int} and quantity {int}")
    public void iCreateAnInvoiceItem(int id, int invoiceId, int menuId, int quantity) throws Exception {
        Map<String, Object> invoiceItem = buildInvoiceItem(id, invoiceId, menuId, quantity);
        sharedState.setMvcResult(mockMvc.perform(post("/invoiceItem/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceItem)))
                .andReturn());
    }

    @When("I get all invoice items")
    public void iGetAllInvoiceItems() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/invoiceItem")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get invoice item with id {int}")
    public void iGetInvoiceItemWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/invoiceItem/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update invoice item with id {int} to menu id {int} and quantity {int}")
    public void iUpdateInvoiceItemWithIdToMenuIdAndQuantity(int id, int menuId, int quantity) throws Exception {
        Map<String, Object> invoiceItem = buildInvoiceItem(id, 1, menuId, quantity);
        sharedState.setMvcResult(mockMvc.perform(put("/invoiceItem/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceItem)))
                .andReturn());
    }

    @When("I delete invoice item with id {int}")
    public void iDeleteInvoiceItemWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/invoiceItem/" + id))
                .andReturn());
    }

    @And("the response should contain invoice item with quantity {int}")
    public void theResponseShouldContainInvoiceItemWithQuantity(int quantity) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains("\"quantity\":" + quantity);
    }

    @And("the response should contain a list of invoice items")
    public void theResponseShouldContainAListOfInvoiceItems() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
