package com.RestaurantBillingManagementSystem.services;

import com.RestaurantBillingManagementSystem.model.FoodType;

import java.util.List;

public interface FoodTypeService {
    List<FoodType> getFoodType();

    FoodType createFoodType(FoodType foodType);

    FoodType getFoodTypeById(int id);

    FoodType updateFoodType(FoodType foodType);

    FoodType deleteFoodTypeById(int id);
}
