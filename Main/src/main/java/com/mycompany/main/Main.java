/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import com.mycompany.main.models.Order;
import com.mycompany.main.models.OrderDatabase;
import com.mycompany.main.models.Product;
import com.mycompany.main.models.ProductDatabase;
import com.mycompany.main.models.User;
import com.mycompany.main.models.UserDatabase;
import com.mycompany.main.ui.WelcomeScreen;
import java.math.BigDecimal;
/**
 *
 * @author _
 */
public class Main{

    public static void main(String[] args) {
        
        //sample users
        User user1 = new User("admin", "admin", "admin");
        User user2 = new User("cedric", "1234", "customer");
        User user3 = new User("cus", "123", "customer");
        
        //sample products
        Product product1 = new Product("B1", new BigDecimal("100"));
        Product product2 = new Product("B2", new BigDecimal("200"));
        Product product3 = new Product("B3", new BigDecimal("300"));
        Product product4 = new Product("B4", new BigDecimal("400"));
        Product product5 = new Product("B5", new BigDecimal("500"));
        
        UserDatabase.getUsers().add(user1);
        UserDatabase.getUsers().add(user2);
        UserDatabase.getUsers().add(user3);
        
        ProductDatabase.getProducts().add(product1);
        ProductDatabase.getProducts().add(product2);
        ProductDatabase.getProducts().add(product3);
        ProductDatabase.getProducts().add(product4);
        ProductDatabase.getProducts().add(product5);
        
        //instantiate welcomescreen
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.main();
    }
}
