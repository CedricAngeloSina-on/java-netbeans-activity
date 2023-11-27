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
public class AdministratorUpdateOrdersStatusScreen implements UserInputInterface, OrdersInterface{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String checkOrderReference() {
        boolean continueLoop = true;
        String returnedOrderReference = null;
        
        while (continueLoop) {
            System.out.print("Order Reference : ");
            String reference = scanner.nextLine().trim();
            
            if (reference == null || reference.isEmpty()) System.out.println("ERROR: Invalid input.");
            else {
                try {            
                    OrderDatabase orderDatabase = new OrderDatabase();
                    List<Order> orders = orderDatabase.getOrders();

                    boolean orderReferenceFound = false; // A flag to track if the user is found

                    for (Order order : orders) {
                        if (order.getOrderReference().equals(reference)) {
                            orderReferenceFound = true;
                            returnedOrderReference = reference;
                            continueLoop = false;
                        }
                    }

                    if (!orderReferenceFound) {
                        System.out.println("ERROR: Order does not exist.");
                        AdministratorManageOrdersScreen ordersScreen = new AdministratorManageOrdersScreen();
                        pressEnterToRedirect(ordersScreen);
                    }
                }
                catch (Exception e) {
                    System.out.println("ERROR: Invalid input.");
                }
            }
        }
        return returnedOrderReference;
    }

    @Override
    public boolean showConfirmationMessage() {
        boolean continueLoop = true;
        boolean returnedConfirmation = false;
        
        while (continueLoop) {
            System.out.print("Are you sure you want to mark this order as delivered (Y/N) : ");
            String confirmation = scanner.nextLine().trim(); 

            if (confirmation == null || confirmation.isEmpty()) System.out.println("ERROR : Invalid input.");
            else if (confirmation.equalsIgnoreCase("Y")) {
                returnedConfirmation = true;
                continueLoop = false;
            }
            else if (confirmation.equalsIgnoreCase("N")) {
                returnedConfirmation = false;
                continueLoop = false;
            }
            else System.out.println("ERROR: Invalid character.");
        }     
        return returnedConfirmation;
    }
    
    @Override
    public void updateOrderStatusByReference(String reference) {
        List<Order> orders = OrderDatabase.getOrders();
        for (Order order : orders) {
            if (order.getOrderReference().equals(reference)) {
                order.setOrderStatus("DELIVERED");
                System.out.println("Order updated successfully.");
                AdministratorManageProductsScreen manageProductsScreen = new AdministratorManageProductsScreen();
                pressEnterToRedirect(manageProductsScreen);
                return; 
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
                if (pressEnter.isEmpty() && (redirect instanceof AdministratorManageOrdersScreen ordersScreen)) {
                    continueLoop = false;
                    ordersScreen.main();
                }
                else if (pressEnter.isEmpty() && (redirect instanceof AdministratorManageProductsScreen productsScreen)) {
                    continueLoop = false;
                    productsScreen.main();
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
        System.out.println("* UPDATE ORDER STATUS *");
        System.out.println("***********************");

        String orderReference =  checkOrderReference();
        
        boolean confirmation = showConfirmationMessage();
        
        if (confirmation == true) updateOrderStatusByReference(orderReference);
        else {
            System.out.println("Action canceled.");
            AdministratorManageProductsScreen manageProductsScreen = new AdministratorManageProductsScreen();
            pressEnterToRedirect(manageProductsScreen);
        }
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
    public void redirectScreen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
