/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

import java.util.Date;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 *
 * @author _
 */
public class Order {
    
    private int userID;
    private Date orderDate;
    private String orderReference;
    private String productName;
    private BigDecimal productPrice; 
    private int orderQuantity;
    private BigDecimal orderTotal;
    private String orderStatus;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    //setters
    public void setOrderDate() {
        this.orderDate = new Date();
    }
        
    public void setOrderReference() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        
        this.orderReference = sb.toString();
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
    
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
    
    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    //getters
    public String getOrderDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(orderDate);
    }
        
    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
