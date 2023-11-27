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
public class ProductDatabase {
    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }
}
