package com.RestaurantBillingManagementSystem.bdd;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

@Component
@io.cucumber.spring.ScenarioScope
public class SharedTestState {
    private MvcResult mvcResult;

    public MvcResult getMvcResult() {
        return mvcResult;
    }

    public void setMvcResult(MvcResult mvcResult) {
        this.mvcResult = mvcResult;
    }
}
