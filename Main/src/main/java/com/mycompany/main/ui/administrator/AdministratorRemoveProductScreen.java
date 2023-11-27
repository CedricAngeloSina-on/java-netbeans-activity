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
public class AdministratorRemoveProductScreen implements UserInputInterface, ProductsInterface{
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public String getValidProductID() {
        boolean continueLoop = true;
        String returnedValidProductID = null;
        
        while (continueLoop) {
            System.out.print("Product ID : ");
            String userInputProductID = scanner.nextLine().trim();

            if (userInputProductID.isEmpty()) System.out.println("ERROR: Invalid input.");
            else {
                try {
                    List<Product> products = ProductDatabase.getProducts();
                    boolean productIDExists = false;

                    for (Product productToRemove : products) {
                        String checkProductID = Integer.toString(productToRemove.getProductID()); 
                        if (checkProductID.equals(userInputProductID)) {
                            productIDExists = true;
                            break;
                        }
                    }
                    if (productIDExists) {
                        continueLoop = false;
                        returnedValidProductID = userInputProductID;
                    }
                    else System.out.println("ERROR: Invalid number.");
                    
                } catch (Exception e) {
                    System.out.println("ERROR: Invalid number.");
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
            System.out.print("Are you sure you want to remove this product (Y/N): ");
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
    public void removeProductByID(int productID) {
        List<Product> products = ProductDatabase.getProducts();

        for (Product product : products) {
            if (product.getProductID() == productID) {
                products.remove(product);
                System.out.println("Product removed successfully!");
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
            }
            catch (Exception e) {
                System.out.println("ERROR : Invalid input.");
            }
        }
    }
    
    public void main() {
        System.out.println("***********************");
        System.out.println("*    REMOVE PRODUCT   *");
        System.out.println("***********************");
        
        String productID = getValidProductID(); //
        int parsedProductID = Integer.parseInt(productID);
        
        boolean confirmation = showConfirmationMessage();
        
        if (confirmation == true) removeProductByID(parsedProductID);
        else {
            System.out.println("Action canceled.");
            AdministratorManageProductsScreen manageProductsScreen = new AdministratorManageProductsScreen();
            pressEnterToRedirect(manageProductsScreen);
        }
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
    public String getValidProductName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BigDecimal getValidProductPrice() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void displayProducts(List<Product> products) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void redirectScreen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void checkProductID(int productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateProduct(int productID, String newProductName, BigDecimal newProductPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
