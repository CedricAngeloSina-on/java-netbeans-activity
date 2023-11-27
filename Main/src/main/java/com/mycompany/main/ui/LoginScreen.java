/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui;

import com.mycompany.main.ui.administrator.AdministratorHomeScreen;
import com.mycompany.main.models.User;
import com.mycompany.main.models.UserDatabase;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.ui.customer.CustomerHomeScreen;
import java.util.Scanner;
import com.mycompany.main.interfaces.CredentialsInterface;

/**
 *
 * @author _
 */
public class LoginScreen implements UserInputInterface, CredentialsInterface{
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public void pressEnterToRedirect(Object redirect) {
        boolean continueLoop = true;
        
        while (continueLoop) {        
            try {
                System.out.println("Press \"ENTER\" to continue...");
                
                String pressEnter = scanner.nextLine().trim();
                if (pressEnter.isEmpty() && (redirect instanceof WelcomeScreen welcomeScreen)) {
                    continueLoop = false;
                    welcomeScreen.main();
                }
                else System.out.println("ERROR : Invalid input.");
            }
            catch (Exception e) {
                System.out.println("ERROR : Invalid input.");
            }
        }
    }
    
    @Override
    public String inputUsername() {
        System.out.print("Username : ");
        String userInputUsername = scanner.nextLine().trim();
        return userInputUsername;
    }

    @Override
    public String inputPassword() {
        System.out.print("Password : ");
        String userInputPassword = scanner.nextLine().trim();
        return userInputPassword;
    }

    @Override
    public void loginCheck(String username, String password) {
        boolean userFound = false;

        for (User user : UserDatabase.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (user.getRole().equals("admin")) {
                    UserDatabase.setCurrentLoggedInUser(user);
                    AdministratorHomeScreen administratorHomeScreen = new AdministratorHomeScreen();
                    administratorHomeScreen.main();
                } else if (user.getRole().equals("customer")) {
                    
                    UserDatabase.setCurrentLoggedInUser(user);
                    CustomerHomeScreen customerHomeScreen = new CustomerHomeScreen();
                    customerHomeScreen.main();
                }
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            System.out.println("ERROR : Invalid credentials.");
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            pressEnterToRedirect(welcomeScreen);
        }
    }

    public void main() {
        System.out.println("***********************");
        System.out.println("*        LOGIN        *");
        System.out.println("***********************");

        String username = inputUsername();
        String password = inputPassword();
        
        loginCheck(username, password);
    }

    @Override
    public void redirectScreen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean showConfirmationMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String newPassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateUser(User updatedUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

