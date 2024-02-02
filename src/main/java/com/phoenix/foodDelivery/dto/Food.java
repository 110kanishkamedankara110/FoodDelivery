package com.phoenix.foodDelivery.dto;

import java.util.LinkedList;
import java.util.List;

public class Food {
    protected String name;
    protected List<Topping> toppings = new LinkedList();
    protected double price;
    protected String description;
    protected String commands;

    public String getCommands() {
        return commands;
    }



    protected Food(Builder b) {
        this.name = b.name;
        this.price = b.price;
        this.description = b.description;
        this.commands=b.commands;
    }

    protected Food() {

    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", toppings=" + toppings +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", commands='" + commands + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(Topping topping) {
        toppings.add(topping);
    }

    public static class Builder {
        protected String name;
        protected double price;
        protected String description;
        protected String commands;

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }


        public Builder setName(String name) {
            this.name = name;
            return this;

        }
        public Builder setCommands(String commands) {
            this.commands = commands;
            return this;
        }
        public Builder setDesc(String description) {
            this.description = description;
            return this;

        }

        public Food getInstance() {
            return new Food(this);
        }
    }


}
