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
public interface ProductsInterface {
    
    void displayProducts(List<Product> products);
    
    void addProduct(String addProductName, BigDecimal addProductPrice);
    
    Product getProductByID(int productID);
    
    void removeProductByID(int productID);
    
    void updateProduct(int productID, String newProductName, BigDecimal newProductPrice);
    
    void checkProductID(int productID);
    
    String getValidProductName();
    
    BigDecimal getValidProductPrice();
    
    String getValidProductID();
}
