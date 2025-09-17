package com.example.productcatalogservice_june2025.controllers;

import com.example.productcatalogservice_june2025.dtos.ProductDto;
import com.example.productcatalogservice_june2025.models.Product;
import com.example.productcatalogservice_june2025.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        // Dummy response
        return null;
    }

    // Read for @PathVariable, @RequestParam, @QueryParam
    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long productId) {
        return null;
    }

    @PostMapping("products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }
}
