/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.administrator;

import com.mycompany.main.models.Product;
import com.mycompany.main.models.ProductDatabase;
import java.util.List;
import com.mycompany.main.interfaces.ProductsInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import java.math.BigDecimal;
import java.util.Scanner;


/**
 *
 * @author _
 */
public class AdministratorManageProductsScreen implements UserInputInterface, ProductsInterface{
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
            System.out.print("What do you want to do : ");
            String input = scanner.nextLine().trim();
            
            if (input == null || input.isEmpty()) System.out.println("ERROR : Invalid input.");
            else {
                try {
                    int intValue = Integer.parseInt(input);

                    switch (intValue) {
                        case 0:
                            continueLoop = false;
                            AdministratorHomeScreen administratorHomeScreen = new AdministratorHomeScreen();
                            administratorHomeScreen.main();
                            break;
                        case 1:
                            continueLoop = false;
                            AdministratorAddProductScreen administratorAddProductScreen = new AdministratorAddProductScreen();
                            administratorAddProductScreen.main();
                            break;
                        case 2:
                            continueLoop = false;
                            AdministratorRemoveProductScreen administratorRemoveProductScreen = new AdministratorRemoveProductScreen();
                            administratorRemoveProductScreen.main();
                            break;
                        case 3:
                            continueLoop = false;
                            AdministratorUpdateProductsScreen administratorUpdateProductsScreen = new AdministratorUpdateProductsScreen();
                            administratorUpdateProductsScreen.main();
                        case 4:
                            continueLoop = false;
                            AdministratorFilterProductsScreen administratorFilterProductsScreen = new AdministratorFilterProductsScreen();
                            administratorFilterProductsScreen.main();
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
    
    public void main(){
        System.out.println("***********************");
        System.out.println("*       PRODUCTS      *");
        System.out.println("***********************");
        
        List<Product> products = ProductDatabase.getProducts();
        displayProducts(products);
        
        System.out.println("***********************");
        System.out.println("1 - Add New Product");
        System.out.println("2 - Remove Product");
        System.out.println("3 - Update Product");
        System.out.println("4 - Filter");
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
    public String getValidProductID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void checkProductID(int productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public void updateProduct(int productID, String newProductName, BigDecimal newProductPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
