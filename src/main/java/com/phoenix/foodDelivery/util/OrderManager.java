package com.phoenix.foodDelivery.util;


import com.phoenix.foodDelivery.dto.Order;

public class OrderManager {
    private final OrderHandler ACCEPTING = new AcceptingHandler();
    private final OrderHandler COOKING = new CookingHandler();
    private final OrderHandler PACKING = new PackingHandler();
    private final OrderHandler HANDOVER = new HandoverHandler();

    public OrderManager() {
        ACCEPTING.setNextOrderHandler(COOKING);
        COOKING.setNextOrderHandler(PACKING);
        PACKING.setNextOrderHandler(HANDOVER);
    }

    public  void  placeOrder(Order o) {
        new Thread(() -> {
            ACCEPTING.handle(o);
        }).start();
    }

    public void cook(Order o){
        new Thread(() -> {
            COOKING.handle(o);
        }).start();
    }

    public void pack(Order o){
        new Thread(() -> {
            PACKING.handle(o);
        }).start();
    }

    public void handOver(Order o){
        new Thread(() -> {
            HANDOVER.handle(o);
        }).start();
    }
}
