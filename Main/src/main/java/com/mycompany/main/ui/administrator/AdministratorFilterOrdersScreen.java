/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.administrator;

import com.mycompany.main.interfaces.FilterOrdersInterface;
import com.mycompany.main.interfaces.FilterParametersInterface;
import com.mycompany.main.interfaces.OrdersInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.Order;
import com.mycompany.main.models.OrderDatabase;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class AdministratorFilterOrdersScreen implements UserInputInterface, FilterOrdersInterface, OrdersInterface, FilterParametersInterface{
    private Scanner scanner = new Scanner(System.in);
    List<Order> orders = OrderDatabase.getOrders();
    
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
                    AdministratorManageOrdersScreen manageOrdersScreen = new AdministratorManageOrdersScreen();
                    
                    switch (intValue) {
                        case 0:
                            continueLoop = false;
                            manageOrdersScreen.main();
                            break;
                        case 1:
                            continueLoop = false;
                            Date orderStartDate = getOrderStartDateFilter();
                            Date orderEndDate = getOrderEndDateFilter();
                            List<Order> filterOrdersByDateRange = filterOrdersByDateRange(orders, orderStartDate, orderEndDate);
                            displayOrders(filterOrdersByDateRange);
                            pressEnterToRedirect(manageOrdersScreen);
                            break;
                        case 2:
                            continueLoop = false;
                            String referenceFilter = getReferenceFilter();
                            List<Order> filteredOrdersByReference = filterOrdersByReference(orders, referenceFilter);
                            displayOrders(filteredOrdersByReference);
                            pressEnterToRedirect(manageOrdersScreen);
                            break;
                        case 3:
                            continueLoop = false;
                            String nameFilter = getNameFilter();
                            List<Order> filterOrdersByName = filterOrdersByName(orders, nameFilter);
                            displayOrders(filterOrdersByName);
                            pressEnterToRedirect(manageOrdersScreen);
                            break;
                        case 4:
                            continueLoop = false;
                            BigDecimal minTotalFilter = getMinPriceOrTotalFilter();
                            BigDecimal maxTotalFilter = getMaxPriceOrTotalFilter();
                            List<Order> filterOrdersByTotalRange = filterOrdersByTotalRange(orders, minTotalFilter, maxTotalFilter);
                            displayOrders(filterOrdersByTotalRange);
                            pressEnterToRedirect(manageOrdersScreen);
                            break;
                        case 5:
                            continueLoop = false;
                            String statusFilter = getOrderStatusFilter();
                            List<Order> filterOrdersByStatus = filterOrdersByStatus(orders, statusFilter);
                            displayOrders(filterOrdersByStatus);
                            pressEnterToRedirect(manageOrdersScreen);
                            break;
                        case 6:
                            continueLoop = false;
                            Date startDate = getOrderStartDateFilter();
                            Date endDate = getOrderEndDateFilter();
                            String reference = getReferenceFilter();
                            String name = getNameFilter();
                            BigDecimal minTotal = getMinPriceOrTotalFilter();
                            BigDecimal maxTotal = getMaxPriceOrTotalFilter();
                            String status = getOrderStatusFilter();
                            List<Order> filteredOrders = filterOrders(orders, 
                                                                    startDate, 
                                                                    endDate, 
                                                                    reference, 
                                                                    name,
                                                                    minTotal, 
                                                                    maxTotal,
                                                                    status);
                            displayOrders(filteredOrders);
                            pressEnterToRedirect(manageOrdersScreen);
                            break;
                        default:
                            System.out.println("Invalid number.");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
        
    @Override
    public Date getOrderStartDateFilter() {
        boolean continueLoop = true;
        Date startDateFilter = null;
                    
        while (continueLoop) {
            System.out.print("Start Date : ");
            String inputStartDate = scanner.nextLine().trim();
        
            if (inputStartDate == null || inputStartDate.isEmpty()) {
                continueLoop = false;
            } 
            else {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    startDateFilter = dateFormat.parse(inputStartDate);
                    continueLoop = false;
                }
                catch (ParseException e) {
                    System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
                }
            }
        }
        return startDateFilter;
    }

    @Override
    public Date getOrderEndDateFilter() {
        boolean continueLoop = true;
        Date endDateFilter = null;
        
        while (continueLoop) {
            System.out.print("End Date : ");
            String inputEndDate = scanner.nextLine().trim();
            
            if (inputEndDate == null || inputEndDate.isEmpty()) {
                continueLoop = false;
            } 
            else {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    endDateFilter = dateFormat.parse(inputEndDate);
                    continueLoop = false;
                }
                catch (ParseException e) {
                    System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
                }
            }
        }
        return endDateFilter;
    }
     
    @Override
    public String getReferenceFilter() {
        System.out.print("Order Reference: ");
        String inputSearchReference = scanner.nextLine().trim();
        return inputSearchReference;
    }
    
    @Override
    public String getNameFilter() {
        System.out.print("Name : ");
        String inputSearchName = scanner.nextLine().trim();
        return inputSearchName;
    }
    
    @Override
    public BigDecimal getMinPriceOrTotalFilter() {
        boolean continueLoop = true;
        BigDecimal inputTotalDecimal = null;
        
        while (continueLoop) {
            System.out.print("Lowest Price : ");
            String inputTotal = scanner.nextLine().trim();
            
            if (inputTotal == null || inputTotal.isEmpty()) {
                inputTotalDecimal = new BigDecimal("0");
                continueLoop = false;
            } else {
                try {
                    inputTotalDecimal = new BigDecimal(inputTotal);
                                        
                    if (inputTotalDecimal.compareTo(BigDecimal.ZERO) >= 0) continueLoop = false;
                    else System.out.println("ERROR: Invalid number. Price must not be negative");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number");
                }
            }
        }
        return inputTotalDecimal;
    }

    @Override
    public BigDecimal getMaxPriceOrTotalFilter() {
        boolean continueLoop = true;
        BigDecimal inputTotalDecimal = null;
        
        while (continueLoop) {
            System.out.print("Highest Price : ");
            String inputTotal = scanner.nextLine().trim();
            
            if (inputTotal == null || inputTotal.isEmpty()) {
                inputTotalDecimal = new BigDecimal("9223372036854775807");
                continueLoop = false;
            } 
            else {
                try {
                    inputTotalDecimal = new BigDecimal(inputTotal);
                                        
                    if (inputTotalDecimal.compareTo(BigDecimal.ZERO) >= 0) continueLoop = false;
                    else System.out.println("ERROR: Invalid number. Price must not be negative");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number");
                }
            }
        }
        return inputTotalDecimal;
    }
    
    @Override
    public String getOrderStatusFilter() {
        System.out.print("Order Status: ");
        String inputOrderStatus = scanner.nextLine().trim();
        return inputOrderStatus;
    }
    
    @Override
    public List<Order> filterOrdersByDateRange(List<Order> orders, Date startDateFilter, Date endDateFilter) {
        List<Order> filteredOrders = new ArrayList<>();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (Order order : orders) {
                Date orderDate = dateFormat.parse(order.getOrderDate());
                
                if ((startDateFilter == null || orderDate.compareTo(startDateFilter) >= 0) &&
                (endDateFilter == null || orderDate.compareTo(endDateFilter) <= 0)) {
                    filteredOrders.add(order);
                } 
                else if ( (orderDate.compareTo(startDateFilter) >= 0 && orderDate.compareTo(endDateFilter) <= 0)) {
                    filteredOrders.add(order);
                } 
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
        }
        return filteredOrders;
    }
    
    @Override
    public List<Order> filterOrdersByReference(List<Order> orders, String referenceFilter) {
        List<Order> filteredOrders = new ArrayList<>();
        
        for (Order order : orders) {
            String orderReference = order.getOrderReference();
            if (orderReference != null && orderReference.contains(referenceFilter)) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
    
    @Override
    public List<Order> filterOrdersByName(List<Order> orders, String nameFilter) {
        List<Order> filteredOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getProductName().contains(nameFilter)) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
    
    @Override
    public List<Order> filterOrdersByTotalRange(List<Order> orders, BigDecimal minTotal, BigDecimal maxTotal) {
        List<Order> filteredOrders = new ArrayList<>();

        for (Order order : orders) {
            BigDecimal orderTotal = order.getOrderTotal();
            if (orderTotal.compareTo(minTotal) >= 0 && orderTotal.compareTo(maxTotal) <= 0) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
    
    
    @Override
    public List<Order> filterOrdersByStatus(List<Order> orders, String statusFilter) {
        List<Order> filteredOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getOrderStatus().contains(statusFilter)) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;    
    }
    
    @Override
    public List<Order> filterOrders(List<Order> orders, 
                                    Date startDateFilter, 
                                    Date endDateFilter, 
                                    String referenceFilter, 
                                    String nameFilter, 
                                    BigDecimal minTotal, 
                                    BigDecimal maxTotal, 
                                    String statusFilter) 
    {
        List<Order> filteredOrders = new ArrayList<>(orders);
        
        if (startDateFilter != null || endDateFilter != null) {
            filteredOrders = filterOrdersByDateRange(filteredOrders, startDateFilter, endDateFilter);
        }
        
        if (referenceFilter != null && !referenceFilter.isEmpty()) {
            filteredOrders = filterOrdersByName(filteredOrders, referenceFilter);
        }
        
        if (nameFilter != null && !nameFilter.isEmpty()) {
            filteredOrders = filterOrdersByName(filteredOrders, nameFilter);
        }
        
        if (minTotal != null && maxTotal != null && minTotal.compareTo(maxTotal) <= 0) {
            filteredOrders = filterOrdersByTotalRange(filteredOrders, minTotal, maxTotal);
        }
        
        if (statusFilter != null && statusFilter.isEmpty()) {
            filteredOrders = filterOrdersByStatus(filteredOrders, statusFilter);
        }
        
        return filteredOrders;
    }

    @Override
    public void displayOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("***********************");
            System.out.println("No orders found.");
            System.out.println("***********************");
        }
        else {
            System.out.println("***********************");
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
            System.out.println("***********************");
        }
    }
    
    @Override
    public void pressEnterToRedirect(Object redirect) {
        boolean continueLoop = true;
        
        while (continueLoop) {        
            try {
                System.out.println("Press \"ENTER\" to continue...");
                
                String pressEnter = scanner.nextLine().trim();
                if (pressEnter.isEmpty() && (redirect instanceof AdministratorManageOrdersScreen manageOrdersScreen)) {
                    continueLoop = false;
                    manageOrdersScreen.main();
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
        System.out.println("*    FILTER ORDERS    *");
        System.out.println("***********************");
        System.out.println("1 - By Dates");
        System.out.println("2 - By Reference");
        System.out.println("3 - By Name");
        System.out.println("4 - By Total Amounts");
        System.out.println("5 - Status");
        System.out.println("6 - Filter Products");
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
    public void updateOrderStatusByReference(String referenceFilter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }












}
