/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

/**
 *
 * @author _
 */
public class User {
    private static int nextUserID = 1;
    
    private int userID;
    private String username;
    private String password;
    private String role; // "admin" or "customer"

    //constructors
    public User(String username, String password, String role) {
        this.userID = nextUserID++;
        this.username = username;
        this.password = password;
        this.role = role;
    }
       
    public int getUserID() {
        return userID;
    }

    public void setUserID() {
        this.userID = nextUserID++;
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }    

    //setters
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

