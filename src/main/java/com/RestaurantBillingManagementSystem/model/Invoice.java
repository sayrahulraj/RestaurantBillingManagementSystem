package com.RestaurantBillingManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @Column(name = "id")
    private int id;
    @CreationTimestamp
    @Column(name = "order_time",updatable = false,insertable = false)
    private Timestamp order_time;
    @Column(name = "order_id")
    private String order_id;
    @Column(name = "customer_id")
    private  int customer_id;

    @Column(name = "charges_id")
    private int charges_id;

    public int getId() {
        return id;
    }

    public int getCharges_id() {
        return charges_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setCharges_id(int charges_id) {
        this.charges_id = charges_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Timestamp getOrder_time() {
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
