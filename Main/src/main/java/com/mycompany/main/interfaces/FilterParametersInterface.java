/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.main.interfaces;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author _
 */
public interface FilterParametersInterface {
    
    //Both products and orders
    String getNameFilter();
    BigDecimal getMinPriceOrTotalFilter();
    BigDecimal getMaxPriceOrTotalFilter();
   
    //Orders only
    Date getOrderStartDateFilter();
    Date getOrderEndDateFilter(); 
    String getReferenceFilter();
    String getOrderStatusFilter();

}
