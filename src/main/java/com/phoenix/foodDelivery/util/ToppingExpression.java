package com.phoenix.foodDelivery.util;

import com.phoenix.foodDelivery.dto.Decorator;
import com.phoenix.foodDelivery.dto.Food;
import com.phoenix.foodDelivery.dto.Topping;

import java.util.List;

public class ToppingExpression implements Expression<Food> {
    Expression<String> expression;
    Food food;
    Keywords kw;

    public ToppingExpression(Expression<String> expression, Food food, Keywords keywords) {
        this.expression = expression;
        this.food = food;
        this.kw=keywords;
    }

    @Override
    public Food interpret() {
        Food extra = new Decorator(this.food) {
            @Override
            public double getPrice() {
                return food.getPrice() + kw.getPrice();
            }

            @Override
            public String getDescription() {
                return food.getDescription() + " With "+kw.toString()+" "+ expression.interpret();
            }

            @Override
            public String getName() {
                return food.getName();
            }

            @Override
            public List<Topping> getToppings() {
                toppings=food.getToppings();
                Topping topping=Topping.getInstance(expression.interpret());
                if(!toppings.contains(topping)&&kw!=Keywords.NO){
                    toppings.add(topping);
                }
                return toppings;
            }
        };
        return extra;
    }
}
