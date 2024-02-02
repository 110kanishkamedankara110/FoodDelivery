package com.phoenix.foodDelivery.util;

import com.phoenix.foodDelivery.dto.Order;
import com.phoenix.foodDelivery.entities.FoodOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CookingHandler extends OrderHandler{
    @Override
    public synchronized void handle(Order o) {

        HashMap<String,List> orderQue = OrderQue.getInstance();

        System.out.println("order "+o.getOrderId()+" Cooking Started");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        String orderID=o.getOrderId();
        Query<FoodOrder> query=session.createQuery("SELECT o FROM FoodOrder o WHERE o.id=:id", FoodOrder.class);
        query.setParameter("id",orderID);
        FoodOrder foodOrder =query.getSingleResult();

        foodOrder.setStatus(Status.COOKING);





        Transaction ta = session.beginTransaction();
        try {
            session.merge(foodOrder);
            ta.commit();
        } catch (Exception e) {
            ta.rollback();
        }finally {
            session.close();
        }

        List l=new LinkedList();
        l.add(this);
        l.add(foodOrder);

        if(orderQue.get(o.getOrderId())==null){
            orderQue.put(o.getOrderId(),l);
        }else{
            orderQue.replace(o.getOrderId(),l);
        }


        try {
            wait();
            System.out.println("order "+ foodOrder.getId()+" Cooking complete");

            if(getNextOrderHandler()!=null) {
                getNextOrderHandler().handle(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
