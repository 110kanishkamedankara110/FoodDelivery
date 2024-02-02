package com.phoenix.foodDelivery.util;

import com.phoenix.foodDelivery.dto.Food;
import com.phoenix.foodDelivery.dto.Order;

import java.util.HashMap;
import java.util.List;

public class OrderCreater {
    public static Order createOrder(HashMap<Food,Integer> food){
        Order o=new Order();
        o.setFoodOrders(food);
        return o;
    }
}
