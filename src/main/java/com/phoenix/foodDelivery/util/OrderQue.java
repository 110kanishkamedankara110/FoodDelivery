package com.phoenix.foodDelivery.util;

import java.util.HashMap;
import java.util.List;

public class OrderQue extends HashMap<String,List> {
    private final static OrderQue ORDER_QUE;

    static {
        ORDER_QUE=new OrderQue();
    }
    private OrderQueListener orderQueListener;

    private OrderQue() {

    }

    public static OrderQue getInstance() {
        return ORDER_QUE;
    }

    @Override
    public List put(String key, List list) {
        System.out.println("changed............");
        if (orderQueListener!=null) {
            orderQueListener.listen(key, list);
        }
        return super.put(key, list);
    }

    @Override
    public List replace(String key, List value) {
        System.out.println("changed............");

        if (orderQueListener!=null) {
            orderQueListener.listen(key, value);
        }
        return super.replace(key, value);
    }

    public void setOrderQueListener(OrderQueListener orderQueListener) {
        this.orderQueListener = orderQueListener;
    }
}
