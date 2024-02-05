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

public class HandoverHandler extends OrderHandler{

    public HandoverHandler(Mediator mediator) {
        super(mediator);
    }
    
    
    @Override
    public synchronized void handle(Order o) {

        HashMap<String,List> orderQue = OrderQue.getInstance();

        mediator.messageAll("order "+o.getOrderId()+" Handover Started");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        String orderID=o.getOrderId();
        Query<FoodOrder> query=session.createQuery("SELECT o FROM FoodOrder o WHERE o.id=:id", FoodOrder.class);
        query.setParameter("id",orderID);
        FoodOrder foodOrder =query.uniqueResult();

        foodOrder.setStatus(Status.HANDOVER);




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

            Session session2 = sessionFactory.openSession();
            Query<FoodOrder> query2=session2.createQuery("SELECT o FROM FoodOrder o WHERE o.id=:id", FoodOrder.class);
            query2.setParameter("id",orderID);

            foodOrder =query2.getSingleResult();
            foodOrder.setStatus(Status.COMPLETED);



            Transaction ta2 = session2.beginTransaction();


            try {
                session2.merge(foodOrder);
                ta2.commit();


            } catch (Exception e) {
                e.printStackTrace();
                ta2.rollback();




            }finally {
                session2.close();
            }

            List l2=new LinkedList();
            l2.add(this);
            l2.add(foodOrder);

            if(orderQue.get(o.getOrderId())==null){
                orderQue.put(o.getOrderId(),l);
            }else{
                orderQue.replace(o.getOrderId(),l);
            }


            mediator.messageAll("order "+ foodOrder.getId()+" Handover complete");



            if(getNextOrderHandler()!=null) {
                getNextOrderHandler().handle(o);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
