package com.phoenix.foodDelivery.util;

import javax.swing.*;

public class FrameSetup {
    public static void setup(JFrame jFrame){
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.addMouseMotionListener(new MouseUtil(jFrame));                
    }
    public static void setupDialog(JDialog jDialog){
        jDialog.setLocationRelativeTo(null);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.addMouseMotionListener(new MouseUtil(jDialog));
    }
}
