package com.RestaurantBillingManagementSystem.services;

import com.RestaurantBillingManagementSystem.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomer();

    Customer createCustomer(Customer customer);

    Customer getCustomerById(int id);

    Customer updateCustomer(Customer customer);

    Customer deleteCustomerById(int id);
}
