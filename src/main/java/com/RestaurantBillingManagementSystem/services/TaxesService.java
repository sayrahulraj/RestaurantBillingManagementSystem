package com.RestaurantBillingManagementSystem.services;

import com.RestaurantBillingManagementSystem.model.Taxes;

import java.util.List;

public interface TaxesService {
    List<Taxes> getTaxes();

    Taxes createTaxes(Taxes taxes);

    Taxes getTaxesById(int id);

    Taxes updateTaxes(Taxes taxes);

    Taxes deleteTaxesById(int id);
}
