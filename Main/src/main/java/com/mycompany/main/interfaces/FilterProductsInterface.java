/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.main.interfaces;

import com.mycompany.main.models.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author _
 */
public interface FilterProductsInterface {
    List<Product> filterProductsByName(List<Product> products, String nameFilter);
    
    List<Product> filterProductsByPriceRange(List<Product> products, BigDecimal minPrice, BigDecimal maxPrice);
    
    List<Product> filterProducts(List<Product> products, String nameFilter, BigDecimal minPrice, BigDecimal maxPrice);
    

}
