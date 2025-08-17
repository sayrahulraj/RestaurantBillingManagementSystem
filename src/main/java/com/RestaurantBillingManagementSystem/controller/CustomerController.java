package com.RestaurantBillingManagementSystem.controller;

import com.RestaurantBillingManagementSystem.model.Customer;
import com.RestaurantBillingManagementSystem.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping(produces = {"application/json"})
    public List<Customer> getCustomer() {
        List<Customer> customer = customerService.getCustomer();
        logger.info("Customer list {} ", customer);
        return customer;
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        logger.info("create customer is invoked {}", customer);
        //sendsms();
        return customerService.createCustomer(customer);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public Customer getCustomerById(@PathVariable("id") int id) {
        logger.info("getMenuById is invoked with menu Id :{}", id);
        return customerService.getCustomerById(id);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public Customer deleteCustomerById(@PathVariable("id") int id) {
        return customerService.deleteCustomerById((id));
    }
}
