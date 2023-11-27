/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

import java.math.BigDecimal;

/**
 *
 * @author _
 */
public class Product {
    private static int nextProductID = 1;
    
    private int productID;
    private String productName;
    private BigDecimal productPrice; // "admin" or "customer"

    public Product(String productName, BigDecimal productPrice) {
        this.productID = nextProductID++;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID() {
        this.productID = nextProductID++;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
