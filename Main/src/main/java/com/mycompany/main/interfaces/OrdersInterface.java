/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.main.interfaces;

import com.mycompany.main.models.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author _
 */
public interface OrdersInterface {
    void displayOrders(List<Order> orders);
    
    void addOrder(Order order);
    
    BigDecimal calculateTotal(BigDecimal productPrice, int quantity);
    
    int getQuantity();
    
    String checkOrderReference();
    
    void updateOrderStatusByReference(String reference);
    
}
