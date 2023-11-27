/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.administrator;

import com.mycompany.main.interfaces.FilterParametersInterface;
import com.mycompany.main.interfaces.ProductsInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.Product;
import com.mycompany.main.models.ProductDatabase;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mycompany.main.interfaces.FilterProductsInterface;
import java.util.Date;

/**
 *
 * @author _
 */
public class AdministratorFilterProductsScreen implements UserInputInterface, FilterProductsInterface, ProductsInterface, FilterParametersInterface{
    private Scanner scanner = new Scanner(System.in);
    List<Product> products = ProductDatabase.getProducts();
    
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
                    AdministratorManageProductsScreen manageProductsScreen = new AdministratorManageProductsScreen();
                    switch (intValue) {
                        case 0:
                            continueLoop = false;
                            manageProductsScreen.main();
                            break;
                        case 1:
                            continueLoop = false;
                            String nameFilter = getNameFilter();
                            List<Product> filterProductsByName = filterProductsByName(products, nameFilter);
                            displayProducts(filterProductsByName);
                            pressEnterToRedirect(manageProductsScreen);
                            break;
                        case 2:
                            continueLoop = false;
                            BigDecimal minPriceFilter = getMinPriceOrTotalFilter();
                            BigDecimal maxPriceFilter = getMaxPriceOrTotalFilter();
                            List<Product> filterProductsByPriceRange = filterProductsByPriceRange(products, minPriceFilter, maxPriceFilter);
                            displayProducts(filterProductsByPriceRange);
                            pressEnterToRedirect(manageProductsScreen);
                            break;
                        case 3:
                            continueLoop = false;
                            String nameFilterProduct = getNameFilter();
                            BigDecimal minPriceFilterProduct = getMinPriceOrTotalFilter();
                            BigDecimal maxPriceFilterProduct = getMaxPriceOrTotalFilter();
                            List<Product> filterProducts = filterProducts(products, nameFilterProduct, minPriceFilterProduct, maxPriceFilterProduct);
                            displayProducts(filterProducts);
                            pressEnterToRedirect(manageProductsScreen);
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
    public String getNameFilter() {
        System.out.print("Name : ");
        String inputSearchName = scanner.nextLine().trim();
        return inputSearchName;
    }
    
    @Override
    public BigDecimal getMinPriceOrTotalFilter() {
        boolean continueLoop = true;
        BigDecimal inputPriceDecimal = null;
        
        while (continueLoop) {
            System.out.print("Lowest Price : ");
            String inputPrice = scanner.nextLine().trim();
            
            if (inputPrice == null || inputPrice.isEmpty()) {
                inputPriceDecimal = new BigDecimal("0");
                continueLoop = false;
            } 
            else {
                try {
                    inputPriceDecimal = new BigDecimal(inputPrice);
                                        
                    if (inputPriceDecimal.compareTo(BigDecimal.ZERO) >= 0) continueLoop = false;
                    else System.out.println("ERROR: Invalid number. Price must not be negative");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number");
                }
            }    
        }
        return inputPriceDecimal;
    }

    @Override
    public BigDecimal getMaxPriceOrTotalFilter() {
        boolean continueLoop = true;
        BigDecimal inputPriceDecimal = null;
        
        while (continueLoop) {
            System.out.print("Highest Price : ");
            String inputPrice = scanner.nextLine().trim();
            
            if (inputPrice == null || inputPrice.isEmpty()) {
                inputPriceDecimal = new BigDecimal("9223372036854775807");
                continueLoop = false;
            } 
            else {
                try {
                    inputPriceDecimal = new BigDecimal(inputPrice);
                                        
                    if (inputPriceDecimal.compareTo(BigDecimal.ZERO) >= 0) continueLoop = false;
                    else System.out.println("ERROR: Invalid number. Price must not be negative");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number");
                }
            }    
        }
        return inputPriceDecimal;
    }
  
    @Override
    public List<Product> filterProductsByName(List<Product> products, String nameFilter) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getProductName().contains(nameFilter)) {
                filteredProducts.add(product);
            }
        }
        
        return filteredProducts;
    }

    @Override
    public List<Product> filterProductsByPriceRange(List<Product> products, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            BigDecimal productPrice = product.getProductPrice();
            if (productPrice.compareTo(minPrice) >= 0 && productPrice.compareTo(maxPrice) <= 0) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }
    
    @Override
    public List<Product> filterProducts(List<Product> products, String nameFilter, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> filteredProducts = new ArrayList<>(products);

        if (nameFilter != null && !nameFilter.isEmpty()) {
            filteredProducts = filterProductsByName(filteredProducts, nameFilter);
        }

        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) <= 0) {
            filteredProducts = filterProductsByPriceRange(filteredProducts, minPrice, maxPrice);
        }

        return filteredProducts;
    }
    
    @Override
    public void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("***********************");
            System.out.println("No products found.");
            System.out.println("***********************");
        } else {
            System.out.println("***********************");
            System.out.println("ID     Name     Price");
            for (Product product : products) {
                System.out.print(product.getProductID());
                System.out.print("      ");
                System.out.print(product.getProductName());
                System.out.print("      ");
                System.out.println(product.getProductPrice());
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
    
    public void main() {
        System.out.println("***********************");
        System.out.println("*   FILTER PRODUCTS   *");
        System.out.println("***********************");
        System.out.println("1 - By Name");
        System.out.println("2 - By Prices");
        System.out.println("3 - Filter Products");
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
    public void updateProduct(int productID, String newProductName, BigDecimal newProductPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void checkProductID(int productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getValidProductID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean showConfirmationMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BigDecimal getValidProductPrice() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getValidProductName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getReferenceFilter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getOrderStatusFilter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getOrderStartDateFilter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Date getOrderEndDateFilter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
