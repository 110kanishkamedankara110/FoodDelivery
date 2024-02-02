package com.phoenix.foodDelivery.util;

public class TerminalExpression implements Expression<String>{
    String name;

    public TerminalExpression(String name) {
        this.name = name;
    }

    @Override
    public String interpret() {
        return name;
    }
}
