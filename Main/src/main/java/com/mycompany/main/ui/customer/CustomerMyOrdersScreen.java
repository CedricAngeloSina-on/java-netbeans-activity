/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.customer;

import com.mycompany.main.interfaces.OrdersInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.Order;
import com.mycompany.main.models.OrderDatabase;
import com.mycompany.main.models.UserDatabase;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class CustomerMyOrdersScreen implements UserInputInterface, OrdersInterface{
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public void redirectScreen() {
        boolean continueLoop = true;
        
        while (continueLoop) {
            System.out.print("What do you want to do? : ");
            String input = scanner.nextLine().trim();
            
            try {
                int intValue = Integer.parseInt(input);

                switch (intValue) {
                case 0:                            
                    continueLoop = false;
                    CustomerHomeScreen customerHomeScreen = new CustomerHomeScreen();
                    customerHomeScreen.main();
                    break;
                case 1:
                    continueLoop = false;
                    CustomerFilterOrdersScreen customerFilterOrdersScreen = new CustomerFilterOrdersScreen();
                    customerFilterOrdersScreen.main();
                    break;                 
                default:
                    System.out.println("ERROR: Invalid number.");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid input.");
            }
        }
    }

    @Override
    public void displayOrders(List<Order> orders) {
        int userID = UserDatabase.getCurrentLoggedInUser();
        List<Order> userOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getUserID() == userID) {
                userOrders.add(order);
            }
        }

        if (userOrders.isEmpty()) System.out.println("No orders found.");
        else {
            System.out.println("Date                 Reference  Name     Price   Qty  Total   Status");
            
            for (Order userOrder : userOrders) {
                    System.out.print(userOrder.getOrderDate());
                    System.out.print("  ");
                    System.out.print(userOrder.getOrderReference());
                    System.out.print("      ");
                    System.out.print(userOrder.getProductName());
                    System.out.print("     ");
                    System.out.print(userOrder.getProductPrice());
                    System.out.print("    ");
                    System.out.print(userOrder.getOrderQuantity());
                    System.out.print("    ");
                    System.out.print(userOrder.getOrderTotal());
                    System.out.print("    ");
                    System.out.println(userOrder.getOrderStatus());
            }
        }
    }
    
    @Override
    public void pressEnterToRedirect(Object redirect) {
        boolean continueLoop = true;
        
        while (continueLoop) {        
            try {
                System.out.println("Press \"ENTER\" to continue...");
                
                String pressEnter = scanner.nextLine().trim();
                if (pressEnter.isEmpty() && (redirect instanceof CustomerHomeScreen customerHomeScreen)) {
                    continueLoop = false;
                    customerHomeScreen.main();
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
        System.out.println("*      MY ORDERS      *");
        System.out.println("***********************");
        
        List<Order> orders = OrderDatabase.getOrders();
        displayOrders(orders);
        
        System.out.println("***********************");
        System.out.println("1 - Filter");
        System.out.println("0 - Back");
        
        redirectScreen();
    }    

    @Override
    public boolean showConfirmationMessage() {
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
    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String checkOrderReference() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateOrderStatusByReference(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
