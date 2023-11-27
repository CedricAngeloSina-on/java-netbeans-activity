/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.ui;

import com.mycompany.main.interfaces.UserInputInterface;
import java.util.Scanner;

/**
 *
 * @author _
 */
public class WelcomeScreen implements UserInputInterface{
    private Scanner scanner = new Scanner(System.in);
    
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
                            System.out.println("Thank you for using our services!");
                            System.exit(0);
                            break;
                        case 1:
                            continueLoop = false;
                            LoginScreen loginScreen = new LoginScreen();
                            loginScreen.main();
                            break;
                        default:
                            System.out.println("ERROR : Invalid number.");
                            break;     
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ERROR : Invalid input.");
                }
            }
        }
    }
    
    public void main() {
        System.out.println("***********************");
        System.out.println("* Welcome to My Shop! *");
        System.out.println("***********************");
        System.out.println("1 - Login");
        System.out.println("0 - Exit");
        
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
