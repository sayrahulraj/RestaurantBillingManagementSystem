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

public class InvoiceStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildInvoice(int id, String orderId, int customerId, int chargesId) {
        Map<String, Object> invoice = new LinkedHashMap<>();
        invoice.put("id", id);
        invoice.put("order_id", orderId);
        invoice.put("customer_id", customerId);
        invoice.put("charges_id", chargesId);
        return invoice;
    }

    @Given("an invoice exists with id {int}, order id {string}, customer id {int} and charges id {int}")
    public void anInvoiceExistsWithIdOrderIdCustomerIdAndChargesId(int id, String orderId, int customerId, int chargesId) throws Exception {
        Map<String, Object> invoice = buildInvoice(id, orderId, customerId, chargesId);
        mockMvc.perform(post("/invoice/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoice)))
                .andExpect(status().isOk());
    }

    @When("I create an invoice with id {int}, order id {string}, customer id {int} and charges id {int}")
    public void iCreateAnInvoiceWithIdOrderIdCustomerIdAndChargesId(int id, String orderId, int customerId, int chargesId) throws Exception {
        Map<String, Object> invoice = buildInvoice(id, orderId, customerId, chargesId);
        sharedState.setMvcResult(mockMvc.perform(post("/invoice/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoice)))
                .andReturn());
    }

    @When("I get all invoices")
    public void iGetAllInvoices() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/invoice")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get invoice with id {int}")
    public void iGetInvoiceWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/invoice/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update invoice with id {int} to order id {string} and customer id {int}")
    public void iUpdateInvoiceWithIdToOrderIdAndCustomerId(int id, String orderId, int customerId) throws Exception {
        Map<String, Object> invoice = buildInvoice(id, orderId, customerId, 1);
        sharedState.setMvcResult(mockMvc.perform(put("/invoice/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoice)))
                .andReturn());
    }

    @When("I delete invoice with id {int}")
    public void iDeleteInvoiceWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/invoice/" + id))
                .andReturn());
    }

    @And("the response should contain order id {string}")
    public void theResponseShouldContainOrderId(String orderId) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains(orderId);
    }

    @And("the response should contain a list of invoices")
    public void theResponseShouldContainAListOfInvoices() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
