/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phoenix.foodDelivery.gui;

import com.phoenix.foodDelivery.dto.Order;
import com.phoenix.foodDelivery.entities.Food;
import com.phoenix.foodDelivery.entities.FoodOrder;
import com.phoenix.foodDelivery.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 * @author BLACKBOX
 */
public class Main extends javax.swing.JFrame {

    private Order fOrder;
    public static Mediator mediator;

    /**
     * Creates new form Main
     */
    public Main() {
        setUndecorated(true);
        initComponents();
        FrameSetup.setup(this);
        this.setup();
        
        
        mediator.messageAll("---------Welcome-------");
        mediator.messageAll("Application Started.......");
        mediator.messageAll("Waiting For orders.........");
        
        
        loadQue();
        
       
        
    }

    private void setup() {
        this.setOpacity(0.99f);

        ManageOrder manageOrder=new ManageOrder();
        OrderFood orderFood=new OrderFood();
        Orders orders=new Orders();
        ToAccept toAccept=new ToAccept();

        mediator = Mediator.getInstance(this,manageOrder,orderFood,orders,toAccept);

        manageOrder.setMediator(mediator);
        orderFood.setMediator(mediator);
        orders.setMediator(mediator);
        toAccept.setMediator(mediator);
        
        
        this.jButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setForeground(Color.red);
                e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setForeground(Color.black);
            }
        });
        change(new MainPannel(mediator));
        this.revalidate();
    }

    private void loadQue() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();


        List<FoodOrder> orders = session.createQuery("SELECT o FROM FoodOrder o", FoodOrder.class).getResultList();
        if (orders.size() > 0) {
            for (FoodOrder order : orders) {
                HashMap<com.phoenix.foodDelivery.dto.Food, Integer> foodList = new HashMap();

                for (Food food : order.getFoodList()) {
                    com.phoenix.foodDelivery.dto.Food f = new com.phoenix.foodDelivery.dto.Food.Builder().setName(food.getName()).setPrice(food.getUnitPrice()).setDesc(food.getDescription()).getInstance();
                    foodList.put(f, food.getQuantity());
                }

                Order fOrder = new Order();
                fOrder.setOrderStatus(order.getStatus());
                fOrder.setFoodOrders(foodList);
                fOrder.setOrderId(order.getId());

                OrderManager om = new OrderManager();

                if (order.getStatus() == Status.COOKING) {
                    om.cook(fOrder);
                } else if (order.getStatus() == Status.PACKING) {
                    om.pack(fOrder);
                } else if (order.getStatus() == Status.HANDOVER) {
                    om.handOver(fOrder);
                } else if (order.getStatus() == Status.ACCEPTING || order.getStatus() == Status.WaitingTillAccepting) {
                    om.placeOrder(fOrder);
                }

            }
        }


    }
    
    

    private void change(JPanel jp) {
        jPanel2.removeAll();
        jPanel2.add(jp);
        this.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(17, 13, 38));

        jButton1.setBackground(new java.awt.Color(255, 0, 153));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(558, 344));
        jPanel2.setLayout(new javax.swing.OverlayLayout(jPanel2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mediator.closeApp();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                com.formdev.flatlaf.FlatIntelliJLaf.setup();
                Main m=new Main();
                m.mediator.runApp();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
