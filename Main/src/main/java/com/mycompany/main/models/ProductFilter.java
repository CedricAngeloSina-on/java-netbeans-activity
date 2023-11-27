/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.models;

/**
 *
 * @author _
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductFilter {

    public List<Product> filterProductsByName(List<Product> products, String nameFilter) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getProductName().contains(nameFilter)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    public List<Product> filterProductsByPriceRange(List<Product> products, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            BigDecimal productPrice = product.getProductPrice();
            if (productPrice.compareTo(minPrice) >= 0 && productPrice.compareTo(maxPrice) <= 0) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    public List<Product> filterProducts(List<Product> products, String nameFilter, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> filteredProducts = new ArrayList<>(products);

        if (nameFilter != null && !nameFilter.isEmpty()) {
            filteredProducts = filterProductsByName(filteredProducts, nameFilter);
        }

        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) <= 0) {
            filteredProducts = filterProductsByPriceRange(filteredProducts, minPrice, maxPrice);
        }

        return filteredProducts;
    }
}
