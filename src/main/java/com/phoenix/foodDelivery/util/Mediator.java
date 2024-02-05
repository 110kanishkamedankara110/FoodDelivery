package com.phoenix.foodDelivery.util;

import com.phoenix.foodDelivery.gui.*;

public class Mediator {

    private static Mediator mediator;

    private Main main;

    private ManageOrder manageOrder;
    private OrderFood orderFood;

    private Orders orders;
    private ToAccept toAccept;

    private Mediator(Main main, ManageOrder manageOrder, OrderFood orderFood, Orders orders, ToAccept toAccept) {
        this.main = main;
        this.manageOrder = manageOrder;
        this.orderFood = orderFood;
        this.orders = orders;
        this.toAccept = toAccept;
    }

    public static Mediator getInstance(Main main, ManageOrder manageOrder, OrderFood orderFood, Orders orders, ToAccept toAccept) {
        if (mediator != null) {
            return mediator;
        }
        return new Mediator(main, manageOrder, orderFood, orders, toAccept);
    }



    public void runApp() {
        if (main != null) {
            main.setVisible(true);
        }
    }

    public void openManageOrder() {
        if (manageOrder != null) {
            manageOrder.setVisible(true);
        }
    }

    public void openOrderFood() {
        if (orderFood != null) {
            orderFood.setVisible(true);
        }
    }

    public void openOrders() {
        if (orders != null) {
            orders.setVisible(true);
        }
    }

    public void openTOAccept() {
        if (toAccept != null) {
            toAccept.setVisible(true);
        }
    }

    public void closeApp() {
        if (main != null) {

            toAccept.dispose();
            manageOrder.dispose();
            orderFood.dispose();
            orders.dispose();

            main.dispose();

        }
    }

    public void closeManageOrder() {
        if (manageOrder != null) {
            manageOrder.setVisible(false);
        }
    }

    public void closeOrderFood() {
        if (orderFood != null) {
            orderFood.setVisible(false);
        }
    }

    public void closeOrders() {
        if (orders != null) {
            orders.setVisible(false);
        }
    }

    public void closeTOAccept() {
        if (toAccept != null) {
            toAccept.setVisible(false);
        }
    }

    public void messageManageOrder(String message) {
        manageOrder.showMessage(message);
    }

    public void messageOrderFood(String message) {
        orderFood.showMessage(message);
    }

    public void messageOrders(String message) {
        orders.showMessage(message);
    }

    public void messageToAccept(String message) {
        toAccept.showMessage(message);
    }

    public void messageAll(String message) {
        messageManageOrder(message);
        messageOrders(message);
        messageToAccept(message);
        messageOrderFood(message);
    }

}
