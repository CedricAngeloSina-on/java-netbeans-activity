/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.administrator;

import com.mycompany.main.interfaces.OrdersInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.Order;
import com.mycompany.main.models.OrderDatabase;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class AdministratorManageOrdersScreen implements UserInputInterface, OrdersInterface{
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public void displayOrders(List<Order> orders) {
        if (orders.isEmpty()) System.out.println("No orders found.");
        else {
            System.out.println("Date                 Reference  Name     Price   Qty  Total   Status");
            for (Order order : orders) {
                System.out.print(order.getOrderDate());
                System.out.print("  ");
                System.out.print(order.getOrderReference());
                System.out.print("      ");
                System.out.print(order.getProductName());
                System.out.print("     ");
                System.out.print(order.getProductPrice());
                System.out.print("    ");
                System.out.print(order.getOrderQuantity());
                System.out.print("    ");
                System.out.print(order.getOrderTotal());
                System.out.print("    ");
                System.out.println(order.getOrderStatus());
            }
        }
    }
    
    @Override
    public void redirectScreen() {
        boolean continueLoop = true;
        
        while (continueLoop) {
            System.out.print("What do you want to do : ");
            String input = scanner.nextLine().trim();
            
            if (input == null || input.isEmpty()) System.out.println("ERROR : Invalid input.");
            else {
                try {
                    int intValue = Integer.parseInt(input);
                    
                    OrderDatabase orderDatabase = new OrderDatabase();
                    List<Order> orders = orderDatabase.getOrders();
                    
                    switch (intValue) {
                        case 0:
                            continueLoop = false;
                            AdministratorHomeScreen administratorHomeScreen = new AdministratorHomeScreen();
                            administratorHomeScreen.main();
                            break;
                        case 1:
                            if (orders.isEmpty()) System.out.println("ERROR: Cannot proceed with action. No orders found.");
                            else {
                                continueLoop = false;
                                AdministratorUpdateOrdersStatusScreen updateOrderScreen = new AdministratorUpdateOrdersStatusScreen();
                                updateOrderScreen.main();
                            }
                            break;
                        case 2:
                            if (orders.isEmpty()) System.out.println("ERROR: Cannot proceed with action. No orders found.");
                            else {
                                continueLoop = false;
                                AdministratorFilterOrdersScreen administratorFilterOrdersScreen = new AdministratorFilterOrdersScreen();
                                administratorFilterOrdersScreen.main(); 
                            }

                            break;
                        default:
                            System.out.println("ERROR : Invalid input.");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ERROR : Invalid input.");
                }            
            }
        }
    }
    
    public void main(){
        System.out.println("***********************");
        System.out.println("*        ORDERS       *");
        System.out.println("***********************");
        
        List<Order> orders = OrderDatabase.getOrders();
        displayOrders(orders);
        
        System.out.println("***********************");
        System.out.println("1 - Mark Order As Delivered");
        System.out.println("2 - Filter");
        System.out.println("0 - Back");
        
        redirectScreen();
    }

    @Override
    public boolean showConfirmationMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void pressEnterToRedirect(Object redirect) {
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
    public void updateOrderStatusByReference(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
