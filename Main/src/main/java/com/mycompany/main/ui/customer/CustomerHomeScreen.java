/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.customer;

import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.UserDatabase;
import com.mycompany.main.ui.WelcomeScreen;
import com.mycompany.main.ui.administrator.AdministratorChangePasswordScreen;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class CustomerHomeScreen implements UserInputInterface{
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
                    UserDatabase.resetCurrentLoggedInUser();
                    WelcomeScreen welcomeScreen = new WelcomeScreen();
                    welcomeScreen.main();
                    break;
                case 1:
                    continueLoop = false;
                    CustomerShopScreen customerShopScreen = new CustomerShopScreen();
                    customerShopScreen.main();
                    break;
                case 2:
                    continueLoop = false;
                    CustomerMyOrdersScreen customerMyOrdersScreen = new CustomerMyOrdersScreen();
                    customerMyOrdersScreen.main();
                    break;                        
                case 3:
                    continueLoop = false;
                    int loggedInUserID = UserDatabase.getCurrentLoggedInUser();
                    CustomerChangePasswordScreen customerChangePasswordScreen = new CustomerChangePasswordScreen(loggedInUserID);
                    customerChangePasswordScreen.main();
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
    
    public void main() {
        System.out.println("***********************");
        System.out.println("*       CUSTOMER      *");
        System.out.println("***********************");
        System.out.println("1 - Shop");
        System.out.println("2 - My Orders");
        System.out.println("3 - Change Password");
        System.out.println("***********************");
        System.out.println("0 - Logout");
        
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
}
