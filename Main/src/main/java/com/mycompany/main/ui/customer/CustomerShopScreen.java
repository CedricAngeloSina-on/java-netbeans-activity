/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.customer;

import com.mycompany.main.interfaces.OrdersInterface;
import com.mycompany.main.interfaces.ProductsInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.Order;
import com.mycompany.main.models.Product;
import com.mycompany.main.models.ProductDatabase;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class CustomerShopScreen implements UserInputInterface, ProductsInterface, OrdersInterface{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayProducts(List<Product> products) {
        if (products.isEmpty()) System.out.println("No products found.");
        else {
            System.out.println("ID     Name     Price");
            for (Product product : products) {
                System.out.print(product.getProductID());
                System.out.print("      ");
                System.out.print(product.getProductName());
                System.out.print("      ");
                System.out.println(product.getProductPrice());
            }
        }
    }
    
    @Override
    public void redirectScreen() {
        boolean continueLoop = true;
        
        while (continueLoop) {
            System.out.print("What do you want to order : ");
            String input = scanner.nextLine().trim();
            
            if (input == null || input.isEmpty()) System.out.println("ERROR : Invalid input.");
            else if (input.equalsIgnoreCase("F")) {
                continueLoop = false;
                CustomerFilterProductsScreen customerFilterProductsScreen = new CustomerFilterProductsScreen();
                customerFilterProductsScreen.main();
            }
            else {
                try {
                    int intValue = Integer.parseInt(input);
                    List<Product> products = ProductDatabase.getProducts();

                    if (intValue == 0) {
                        continueLoop = false;
                        CustomerHomeScreen customerHomeScreen = new CustomerHomeScreen();
                        customerHomeScreen.main();
                    }
                    
                    else if (intValue != 0 && products.isEmpty()) System.out.println("ERROR: Cannot proceed with action. No products found.");
                    else checkProductID(intValue);
                    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
    
    @Override
    public void checkProductID(int productID) {
        
        boolean productIDFound = false; // A flag to track if the user is found
        
        List<Product> products = ProductDatabase.getProducts();
        
        for (Product product : products) {
            if (product.getProductID() == productID) {
                productIDFound = true;
                
                CustomerPlaceOrderScreen placeOrderScreen = new CustomerPlaceOrderScreen(productID);
                placeOrderScreen.main();
                break; // Exit the loop since we found a match.
            } 
        }
        
        if (!productIDFound) System.out.println("ERROR: Invalid input. Product does not exist.");
    }
    
    public void main(){
        System.out.println("***********************");
        System.out.println("*      PRODUCTS       *");
        System.out.println("***********************");
        
        List<Product> products = ProductDatabase.getProducts();
        displayProducts(products);
        
        System.out.println("***********************");
        System.out.println("F - Filter");
        System.out.println("0 - Back");
        
        redirectScreen();
        
    }

    @Override
    public void addProduct(String addProductName, BigDecimal addProductPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Product getProductByID(int productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void removeProductByID(int productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String getValidProductName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BigDecimal getValidProductPrice() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void pressEnterToRedirect(Object redirect) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String getValidProductID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void displayOrders(List<Order> orders) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public BigDecimal calculateTotal(BigDecimal productPrice, int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String checkOrderReference() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean showConfirmationMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateOrderStatusByReference(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateProduct(int productID, String newProductName, BigDecimal newProductPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
