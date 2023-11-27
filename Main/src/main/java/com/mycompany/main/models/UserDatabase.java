/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _
 */
public class UserDatabase {

    private static List<User> users = new ArrayList<>();
    private static int currentLoggedInUser;

    public static int getCurrentLoggedInUser() {
        return currentLoggedInUser;
    }
    
    public static void resetCurrentLoggedInUser() {
        currentLoggedInUser = 0;
    }

    public static void setCurrentLoggedInUser(User user) {
        currentLoggedInUser = user.getUserID();
    }
    
    public static List<User> getUsers() {
        return users;
    }
    
    public static User getUserByID(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }
}
