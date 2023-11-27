/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.customer;

import com.mycompany.main.interfaces.OrdersInterface;
import com.mycompany.main.interfaces.ProductsInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.Order;
import com.mycompany.main.models.OrderDatabase;
import com.mycompany.main.models.Product;
import com.mycompany.main.models.ProductDatabase;
import com.mycompany.main.models.UserDatabase;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class CustomerPlaceOrderScreen implements UserInputInterface, OrdersInterface, ProductsInterface {
    private Scanner scanner = new Scanner(System.in);
    
    private int productID;

    public CustomerPlaceOrderScreen(int productID) {
        this.productID = productID;
    }
    
    @Override
    public Product getProductByID(int productID) {
        List<Product> products = ProductDatabase.getProducts();
        Product foundProduct = null; // Initialize foundProduct to null

        for (Product product : products) {
            if (product.getProductID() == productID) {
                System.out.println("Name : " + product.getProductName());
                System.out.println("Price : " + product.getProductPrice());
                foundProduct = product; 
                break; 
            }
        }
        return foundProduct; 
    }

    @Override
    public int getQuantity() {
        boolean continueLoop = true;
        int returnedQuantity = 0;
        
        while (continueLoop) {
            System.out.print("How many do you want : ");
            String input = scanner.nextLine().trim();

            if (input == null || input.isEmpty()) System.out.println("ERROR : Invalid input.");
            else {
                try {
                    int parsedInputQuantity = Integer.parseInt(input);
                    returnedQuantity = parsedInputQuantity;
                    continueLoop = false;
                } catch (NumberFormatException e) {
                    System.out.println("ERROR : Invalid number.");
                }
            }
        }
        return returnedQuantity;
    }
    
    @Override
    public BigDecimal calculateTotal(BigDecimal productPrice, int quantity) {
        BigDecimal total = productPrice.multiply(BigDecimal.valueOf(quantity));
        System.out.println("That would be Php " + total + ".");
        return total;
    }

    
    @Override
    public boolean showConfirmationMessage() {
        boolean continueLoop = true;
        boolean returnedConfirmation = false;
        
        while (continueLoop) {
            System.out.print("Proceed with your order (Y/N) : ");
            String confirmation = scanner.nextLine().trim();
            
            if (confirmation == null || confirmation.isEmpty()) System.out.println("ERROR : Invalid input.");
            else if (confirmation.equalsIgnoreCase("Y")) {
                continueLoop = false;
                returnedConfirmation = true;
            }
            else if (confirmation.equalsIgnoreCase("N")) {
                continueLoop = false;
                returnedConfirmation = false;
            }
            else System.out.println("ERROR: Invalid character.");
        }
        return returnedConfirmation;
    }
    
    @Override
    public void pressEnterToRedirect(Object redirect) {
        boolean continueLoop = true;
        
        while (continueLoop) {        
            try {
                System.out.println("Press \"ENTER\" to continue...");
                
                String pressEnter = scanner.nextLine().trim();
                if (pressEnter.isEmpty() && (redirect instanceof CustomerShopScreen customerShopScreen)) {
                    continueLoop = false;
                    customerShopScreen.main();
                }
                else System.out.println("ERROR : Invalid input.");
            }
            catch (Exception e) {
                System.out.println("ERROR : Invalid input.");
            }
        }
    }

    public void main() {
        System.out.println("***********************");
        System.out.println("*     PLACE ORDER     *");
        System.out.println("***********************");
        
        Product product = getProductByID(productID);
        
        String productName = product.getProductName();
        BigDecimal productPrice = product.getProductPrice();
        
        int quantity = getQuantity();
        BigDecimal total = calculateTotal(productPrice, quantity);
        
        boolean confirmation = showConfirmationMessage();
        
        if (confirmation == true) {
            int userID = UserDatabase.getCurrentLoggedInUser();
            Order order = new Order();
            
            order.setUserID(userID);
            order.setOrderDate();
            order.setOrderReference();
            order.setProductName(productName);
            order.setProductPrice(productPrice);
            order.setOrderQuantity(quantity);
            order.setOrderTotal(total);
            order.setOrderStatus("FOR_DELIVERY");
            
            addOrder(order);
            
            System.out.println("Order placed successfully.");
            CustomerShopScreen customerShopScreen = new CustomerShopScreen();
            pressEnterToRedirect(customerShopScreen);
        } 
        else {
            System.out.println("Action canceled.");
            CustomerShopScreen customerShopScreen = new CustomerShopScreen();
            pressEnterToRedirect(customerShopScreen);
        }
    }

    @Override
    public void displayOrders(List<Order> orders) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addOrder(Order order) {
        List<Order> orders = OrderDatabase.getOrders();
        orders.add(order);
    }
    
    @Override
    public void checkProductID(int productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public String checkOrderReference() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void displayProducts(List<Product> products) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addProduct(String addProductName, BigDecimal addProductPrice) {
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
    public String getValidProductID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void redirectScreen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateOrderStatusByReference(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateProduct(int productID, String newProductName, BigDecimal newProductPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}
