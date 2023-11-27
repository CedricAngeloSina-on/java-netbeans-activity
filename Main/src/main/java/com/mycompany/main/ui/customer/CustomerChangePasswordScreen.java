/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui.customer;

import com.mycompany.main.interfaces.CredentialsInterface;
import com.mycompany.main.interfaces.UserInputInterface;
import com.mycompany.main.models.User;
import com.mycompany.main.models.UserDatabase;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class CustomerChangePasswordScreen implements UserInputInterface, CredentialsInterface{
    private Scanner scanner = new Scanner(System.in);
    private int currentLoggedInUserID;
    
    public CustomerChangePasswordScreen(int userID) {
        this.currentLoggedInUserID = userID;
    }
    
    @Override
    public String inputPassword() {
        System.out.print("Old Password : ");
        String userInputPassword = scanner.nextLine().trim();
        return userInputPassword;
    }
    
    @Override
    public String newPassword() {
        boolean continueLoop = true;
        String returnedNewPassword = null;

        while (continueLoop) {
            String oldPassword = inputPassword();
            System.out.print("New Password : ");
            String newPassword = scanner.nextLine().trim();
            System.out.print("Confirm New Password : ");
            String confirmNewPassword = scanner.nextLine().trim();
            
            User user = UserDatabase.getUserByID(currentLoggedInUserID);
            if (user.getPassword().equals(oldPassword)) {
                if (newPassword.equals(confirmNewPassword)) {
                    returnedNewPassword = newPassword;
                    continueLoop = false;
                } else {
                    System.out.println("New password and confirm password do not match.");
                }
            } else {
                System.out.println("Old password is incorrect.");
            }
        }
        return returnedNewPassword;
    }
        
    @Override
    public boolean showConfirmationMessage() {
        boolean continueLoop = true;
        boolean returnedConfirmation = false;
        
        while (continueLoop) {
            System.out.print("Are you sure you want to change your password (Y/N): ");
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
    public void pressEnterToRedirect(Object redirect) {
        boolean continueLoop = true;
        
        while (continueLoop) {        
            try {
                System.out.println("Press \"ENTER\" to continue...");
                
                String pressEnter = scanner.nextLine().trim();
                if (pressEnter.isEmpty() && (redirect instanceof CustomerHomeScreen customerHomeScreen)) {
                    continueLoop = false;
                    customerHomeScreen.main();
                }
                else System.out.println("ERROR : Invalid input.");
            }
            catch (Exception e) {
                System.out.println("ERROR : Invalid input.");
            }
        }
    }
    
    @Override
    public void updateUser(User updatedUser) {
        List<User> users = UserDatabase.getUsers();
        int userID = updatedUser.getUserID();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUserID() == userID) {
                users.set(i, updatedUser); 
                return;
            }
        }
    }
    
    public void main() {
        System.out.println("***********************");
        System.out.println("*   CHANGE PASSWORD   *");
        System.out.println("***********************");
        
        String newPassword = newPassword();
        
        boolean confirmation = showConfirmationMessage();
        
        if (confirmation == true) {
            User user = UserDatabase.getUserByID(currentLoggedInUserID);
            user.setPassword(newPassword);
            updateUser(user);
            System.out.println("Password changed successfully.");
            CustomerHomeScreen customerHomeScreen = new CustomerHomeScreen();
            pressEnterToRedirect(customerHomeScreen);
        }
        else {
            System.out.println("Action canceled.");
            CustomerHomeScreen customerHomeScreen = new CustomerHomeScreen();
            pressEnterToRedirect(customerHomeScreen);
        }
    }

    @Override
    public String inputUsername() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void loginCheck(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void redirectScreen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }    
}
