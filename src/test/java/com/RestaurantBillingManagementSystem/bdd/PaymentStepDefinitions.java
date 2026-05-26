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

public class PaymentStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SharedTestState sharedState;

    private Map<String, Object> buildPayment(int id, int invoiceId, int customerId, int paymentTypeId, float totalAmount) {
        Map<String, Object> payment = new LinkedHashMap<>();
        payment.put("id", id);
        payment.put("invoice_id", invoiceId);
        payment.put("customer_id", customerId);
        payment.put("payment_type_id", paymentTypeId);
        payment.put("total_amount", totalAmount);
        return payment;
    }

    @Given("a payment exists with id {int}, invoice id {int}, customer id {int}, payment type id {int} and total amount {double}")
    public void aPaymentExists(int id, int invoiceId, int customerId, int paymentTypeId, double totalAmount) throws Exception {
        Map<String, Object> payment = buildPayment(id, invoiceId, customerId, paymentTypeId, (float) totalAmount);
        mockMvc.perform(post("/payment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isOk());
    }

    @When("I create a payment with id {int}, invoice id {int}, customer id {int}, payment type id {int} and total amount {double}")
    public void iCreateAPayment(int id, int invoiceId, int customerId, int paymentTypeId, double totalAmount) throws Exception {
        Map<String, Object> payment = buildPayment(id, invoiceId, customerId, paymentTypeId, (float) totalAmount);
        sharedState.setMvcResult(mockMvc.perform(post("/payment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andReturn());
    }

    @When("I get all payments")
    public void iGetAllPayments() throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/payment")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I get payment with id {int}")
    public void iGetPaymentWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(get("/payment/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }

    @When("I update payment with id {int} to payment type id {int} and total amount {double}")
    public void iUpdatePaymentWithId(int id, int paymentTypeId, double totalAmount) throws Exception {
        Map<String, Object> payment = buildPayment(id, 1, 1, paymentTypeId, (float) totalAmount);
        sharedState.setMvcResult(mockMvc.perform(put("/payment/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andReturn());
    }

    @When("I delete payment with id {int}")
    public void iDeletePaymentWithId(int id) throws Exception {
        sharedState.setMvcResult(mockMvc.perform(delete("/payment/" + id))
                .andReturn());
    }

    @And("the response should contain payment with total amount {double}")
    public void theResponseShouldContainPaymentWithTotalAmount(double totalAmount) throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.contains("total_amount");
    }

    @And("the response should contain a list of payments")
    public void theResponseShouldContainAListOfPayments() throws Exception {
        String content = sharedState.getMvcResult().getResponse().getContentAsString();
        assert content.startsWith("[");
    }
}
