/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoenix.foodDelivery.util;

/**
 *
 * @author BLACKBOX
 */
public enum Types {
    WARNING("/images/warning.png"),
    SUCCESS("/images/sucess.png"),
    ERROR("/images/error.png");
    private String image;
    Types(String image){
        this.image=image;
    }
    public String getImage() {
        return image;
    }

}
