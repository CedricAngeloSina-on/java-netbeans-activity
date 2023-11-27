/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.main.interfaces;

import com.mycompany.main.models.User;
import java.util.Scanner;

/**
 *
 * @author _
 */
public interface CredentialsInterface {
    Scanner scanner = new Scanner(System.in);
    
    String inputUsername();
    
    String inputPassword();
    
    String newPassword();
    
    void loginCheck(String username, String password);
    
    void updateUser(User updatedUser);
}
