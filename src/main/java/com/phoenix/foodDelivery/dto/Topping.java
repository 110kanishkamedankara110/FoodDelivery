package com.phoenix.foodDelivery.dto;

import java.util.HashMap;

public class Topping {

    private Topping(String name) {
        this.name=name;
    }

    protected String name;
    protected static final HashMap<String, Topping> toppings = new HashMap();

    public String getName() {
        return name;
    }

    public static Topping getInstance(String name) {
        Topping instance = toppings.get(name);
        if (instance == null) {
            Topping t = new Topping(name);
            toppings.put(name,t);
            return t;
        }
        return instance;
    }
}
