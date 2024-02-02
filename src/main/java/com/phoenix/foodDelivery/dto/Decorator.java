package com.phoenix.foodDelivery.dto;

public abstract class Decorator extends Food {
    private Food food;

    public Decorator(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public abstract double getPrice();

    @Override
    public abstract String getDescription();

}
