package com.phoenix.foodDelivery.dto;

import com.phoenix.foodDelivery.util.Status;

import java.util.HashMap;

public class Order {

    private String orderId=String.valueOf(System.currentTimeMillis());

    private HashMap<Food,Integer> foodOrders=new HashMap();
    private Status orderStatus;

    public HashMap<Food, Integer> getFoodOrders() {

        return foodOrders;
    }

    public void setFoodOrders(Food food,Integer quantity) {
        this.foodOrders.put(food,quantity);
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setFoodOrders(HashMap<Food, Integer> foodOrders) {
        this.foodOrders = foodOrders;
    }
}
