package com.phoenix.foodDelivery.util;

import com.phoenix.foodDelivery.dto.Food;
import com.phoenix.foodDelivery.dto.Order;
import com.phoenix.foodDelivery.entities.FoodOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AcceptingHandler extends OrderHandler {

    public AcceptingHandler(Mediator mediator) {
        super(mediator);
    }
    
    @Override
    public synchronized void handle(Order o) {

        mediator.messageAll("order " + o.getOrderId() + " Waiting till accepting");

        OrderQue orderQue = OrderQue.getInstance();


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction ta = session.beginTransaction();

        Query<FoodOrder> query = session.createQuery("SELECT o FROM FoodOrder o WHERE o.id=:id", FoodOrder.class);
        query.setParameter("id", o.getOrderId());
        FoodOrder foodOrder = null;
        if (query.getResultList().size() <= 0) {

            foodOrder = new FoodOrder();
            foodOrder.setStatus(Status.WaitingTillAccepting);
            foodOrder.setId(o.getOrderId());
            HashMap<Food, Integer> foodList = o.getFoodOrders();
            for (Map.Entry<Food, Integer> entry : foodList.entrySet()) {
                Food food = entry.getKey();
                Integer qty = entry.getValue();
                com.phoenix.foodDelivery.entities.Food dbfood = new com.phoenix.foodDelivery.entities.Food();
                dbfood.setName(food.getName());

                dbfood.setDescription(food.getDescription());
                dbfood.setQuantity(qty);
                dbfood.setUnitPrice(food.getPrice());
                dbfood.setOrder(foodOrder);
                foodOrder.setFood(dbfood);
            }

            try {
                session.persist(foodOrder);
                ta.commit();


            } catch (Exception e) {
                ta.rollback();
            }finally {
                session.close();
            }

        }else{
            foodOrder = query.getSingleResult();
        }

        List l = new LinkedList();
        l.add(this);
        l.add(foodOrder);

        if (orderQue.get(o.getOrderId()) == null) {
            orderQue.put(o.getOrderId(), l);
        } else {
            orderQue.replace(o.getOrderId(), l);
        }


        try {
            wait();



            Session session2 = sessionFactory.openSession();
            Query<FoodOrder> query2 = session2.createQuery("SELECT o FROM FoodOrder o WHERE o.id=:id", FoodOrder.class);
            query2.setParameter("id", o.getOrderId());
            foodOrder = query2.getSingleResult();
            foodOrder.setStatus(Status.ACCEPTING);
            Transaction ta2 = session2.beginTransaction();






            try {
                session2.merge(foodOrder);
                ta2.commit();

                
            } catch (Exception e) {
                ta2.rollback();
            } finally {
                session2.close();
            }

            List l2 = new LinkedList();
            l2.add(this);
            l2.add(foodOrder);

            if (orderQue.get(o.getOrderId()) == null) {
                orderQue.put(o.getOrderId(), l);
            } else {
                orderQue.replace(o.getOrderId(), l);
            }


            mediator.messageAll("order " + foodOrder.getId() + " Accepted");



            if (getNextOrderHandler() != null) {
                getNextOrderHandler().handle(o);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
