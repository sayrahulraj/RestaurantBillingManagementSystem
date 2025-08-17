package com.RestaurantBillingManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_type")
public class FoodType {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "food_type_name")
    private String food_type_name;

    public int getId() {
        return id;
    }

    public String getFood_type_name() {
        return food_type_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFood_type_name(String food_type_name) {
        this.food_type_name = food_type_name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
