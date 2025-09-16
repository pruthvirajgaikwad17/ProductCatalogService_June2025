package com.example.productcatalogservice_june2025.controllers;

import com.example.productcatalogservice_june2025.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        // Dummy response
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        System.out.println("check branch merge and pr");
        return productList;
    }
}
