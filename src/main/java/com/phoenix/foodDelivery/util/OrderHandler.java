package com.phoenix.foodDelivery.util;

import com.phoenix.foodDelivery.dto.Order;

public abstract class OrderHandler {
    private OrderHandler nextOrderHandler;


    public void setNextOrderHandler(OrderHandler orderHandler) {
        this.nextOrderHandler = orderHandler;
    }

    public OrderHandler getNextOrderHandler() {
        return nextOrderHandler;
    }

    public abstract void handle(Order o);

    public synchronized void resume() {
        notifyAll();
    }



}
