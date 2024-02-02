package com.phoenix.foodDelivery.util;

import javax.swing.*;
import java.awt.event.*;

public class MouseUtil extends MouseAdapter implements MouseMotionListener {

    private int mouseX;
    private int mouseY;
    private JFrame jFrame;
    private JDialog jDialog;

    public MouseUtil(JFrame jFrame) {
        this.jFrame = jFrame;
        this.jFrame.addMouseListener(this);
    }

    public MouseUtil(JDialog jDialog) {
        this.jDialog = jDialog;
        this.jDialog.addMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int realPosX = e.getXOnScreen() - mouseX;
        int realPosY = e.getYOnScreen() - mouseY;

        if (jDialog != null) {
            jDialog.setLocation(realPosX, realPosY);
        }
        if (jFrame != null) {
            jFrame.setLocation(realPosX, realPosY);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

}