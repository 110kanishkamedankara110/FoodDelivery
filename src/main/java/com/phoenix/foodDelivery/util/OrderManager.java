package com.phoenix.foodDelivery.util;


import com.phoenix.foodDelivery.dto.Order;
import com.phoenix.foodDelivery.gui.Main;

public class OrderManager {
    
    Mediator m=Main.mediator;
    
    private final OrderHandler ACCEPTING = new AcceptingHandler(m);
    private final OrderHandler COOKING = new CookingHandler(m);
    private final OrderHandler PACKING = new PackingHandler(m);
    private final OrderHandler HANDOVER = new HandoverHandler(m);

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
