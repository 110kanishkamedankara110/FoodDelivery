package com.phoenix.foodDelivery.entities;

import jakarta.persistence.*;

@Entity
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double unitPrice;
    private Integer quantity;
    @ManyToOne
    private FoodOrder foodOrder;


    public FoodOrder getOrder() {
        return foodOrder;
    }

    public void setOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
