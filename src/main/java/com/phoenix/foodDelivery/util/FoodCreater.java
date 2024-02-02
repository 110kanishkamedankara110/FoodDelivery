package com.phoenix.foodDelivery.util;

import com.phoenix.foodDelivery.dto.Food;

public class FoodCreater {
    public static Food createFood(String name,double price,String commands){
        return new Food.Builder().setDesc(name).setName(name).setPrice(price).setCommands(commands).getInstance();
    }
}
