/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.administrator;

import com.mycompany.main.interfaces.ProductsInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.Product;
import com.mycompany.main.models.ProductDatabase;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class AdministratorUpdateProductsScreen implements UserInputInterface, ProductsInterface{
    private Scanner scanner = new Scanner(System.in);
    


    @Override
    public String getValidProductName() {
        boolean continueLoop = true;
        String returnedProductName = null;
        
        while (continueLoop) {
            System.out.print("Name : ");
            String userInputProductName = scanner.nextLine().trim();

            if (userInputProductName == null || userInputProductName.isEmpty()) {
                System.out.println("ERROR: Invalid input.");
            } else {
                try {
                    List<Product> products = ProductDatabase.getProducts();
                    boolean productNameExists = false;

                    for (Product productToAdd : products) {
                        if (productToAdd.getProductName().equals(userInputProductName)) {
                            productNameExists = true;
                            break;
                        }
                    }

                    if (productNameExists) {
                        System.out.println("ERROR: Invalid product name.");
                    } else {
                        continueLoop = false;
                        returnedProductName = userInputProductName;
                    }

                } catch (Exception e) {
                    System.out.println("ERROR: Invalid product name.");
                }
            }
        }
        return returnedProductName;
    }

    @Override
    public BigDecimal getValidProductPrice() {
        boolean continueLoop = true;
        BigDecimal returnedProductPrice = null;
        
        while (continueLoop) {
            System.out.print("Price : ");
            String userInputPrice = scanner.nextLine().trim();
            
            if (userInputPrice == null || userInputPrice.isEmpty()) {
                System.out.println("ERROR: Invalid input.");
            }
            else {            
                try {
                    BigDecimal userInputPriceDecimal = new BigDecimal(userInputPrice);
                    
                    if (userInputPriceDecimal.compareTo(BigDecimal.ZERO) >= 0) {
                        returnedProductPrice = userInputPriceDecimal;
                        continueLoop = false;
                    } else {
                        System.out.println("ERROR: Invalid number. Price must not be negative");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: Invalid number.");
                }
            }

        }
        return returnedProductPrice;
    }   


    @Override
    public String getValidProductID() {
        boolean continueLoop = true;
        String returnedValidProductID = null;
        
        while (continueLoop) {
            System.out.print("Product ID : ");
            String userInputProductID = scanner.nextLine().trim();

            if (userInputProductID == null || userInputProductID.isEmpty()) {
                System.out.println("ERROR: Invalid input.");
            }
            else {
                try {
                    List<Product> products = ProductDatabase.getProducts();
                    boolean productIDExists = false;

                    for (Product productToUpdate : products) {
                        String checkProductID = Integer.toString(productToUpdate.getProductID()); 
                        if (checkProductID.equals(userInputProductID)) {
                            productIDExists = true;
                            break;
                        }
                    }
                    
                    if (productIDExists) {
                        continueLoop = false;
                        returnedValidProductID = userInputProductID;
                    } else {
                        System.out.println("ERROR: product does not exist.");
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: product does not exist.");
                }
            }
        }
        return returnedValidProductID;
    }    
    
    @Override
    public boolean showConfirmationMessage() {
        boolean continueLoop = true;
        boolean returnedConfirmation = false;
        
        while (continueLoop) {
            System.out.print("Are you sure you want to add this product (Y/N): ");
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
    public void updateProduct(int productID, String newProductName, BigDecimal newProductPrice) {
        List<Product> products = ProductDatabase.getProducts();

        for (Product product : products) {
            if (product.getProductID() == productID) {
                product.setProductName(newProductName);
                product.setProductPrice(newProductPrice);
                System.out.println("Product updated successfully.");
                AdministratorManageProductsScreen manageProductsScreen = new AdministratorManageProductsScreen();
                pressEnterToRedirect(manageProductsScreen);
                break;
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
                if (pressEnter.isEmpty() && (redirect instanceof AdministratorManageProductsScreen manageProductsScreen)) {
                    continueLoop = false;
                    manageProductsScreen.main();
                }
                else System.out.println("ERROR : Invalid input.");
            }
            catch (Exception e) {
                System.out.println("ERROR : Invalid input.");
            }
        }
    }
    
    public void main(){
        System.out.println("***********************");
        System.out.println("*    UPDATE PRODUCT    ");
        System.out.println("***********************");
        
        String productID = getValidProductID();
        int parsedProductID = Integer.parseInt(productID);
        
        String productName = getValidProductName(); 
        BigDecimal productPrice = getValidProductPrice();
        
        boolean confirmation = showConfirmationMessage();
        
        if (confirmation == true) updateProduct(parsedProductID, productName, productPrice);
        else {
            System.out.println("Action canceled.");
            AdministratorManageProductsScreen manageProductsScreen = new AdministratorManageProductsScreen();
            pressEnterToRedirect(manageProductsScreen);
        }
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
    public void checkProductID(int productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void redirectScreen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Product getProductByID(int productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
