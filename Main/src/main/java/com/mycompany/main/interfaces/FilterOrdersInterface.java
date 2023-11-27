/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.main.interfaces;

import com.mycompany.main.models.Order;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author _
 */
public interface FilterOrdersInterface {
    
    List<Order> filterOrdersByDateRange(List<Order> orders, Date startDateFilter, Date endDateFilter);
    
    List<Order> filterOrdersByReference(List<Order> orders, String referenceFilter);
    
    List<Order> filterOrdersByName(List<Order> orders, String nameFilter);
    
    List<Order> filterOrdersByTotalRange(List<Order> orders, BigDecimal minTotal, BigDecimal maxTotal);
    
    List<Order> filterOrdersByStatus(List<Order> orders, String statusFilter);
    
    List<Order> filterOrders(List<Order> orders, 
                            Date startDateFilter, 
                            Date endDateFilter, 
                            String referenceFilter, 
                            String nameFilter, 
                            BigDecimal minTotal, 
                            BigDecimal maxTotal, 
                            String statusFilter);
}
