/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.main.interfaces;

import java.util.Scanner;

/**
 *
 * @author _
 */
public interface UserInputInterface{
    Scanner scanner = new Scanner(System.in);
    
    void redirectScreen();
    
    boolean showConfirmationMessage();
    
    void pressEnterToRedirect(Object redirect);
}
