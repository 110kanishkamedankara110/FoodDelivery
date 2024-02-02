package com.phoenix.foodDelivery.util;

public enum Keywords {
    EXTRA(+100d){
        @Override
        public String toString() {

            return "Extra";
        }


    }, LESS(-10d){
        @Override
        public String toString() {
            return "Less";
        }

    }, NO(-50d){
        @Override
        public String toString() {
            return "No";
        }

    };

    private double price;

    private Keywords(Double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
     return "Extra,Less,No";
    }
}
