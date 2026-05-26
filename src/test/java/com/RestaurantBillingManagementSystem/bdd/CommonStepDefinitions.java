package com.RestaurantBillingManagementSystem.bdd;

import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommonStepDefinitions {

    @Autowired
    private SharedTestState sharedState;

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int status) {
        assertNotNull(sharedState.getMvcResult(), "MvcResult should not be null");
        assertEquals(status, sharedState.getMvcResult().getResponse().getStatus());
    }
}
