package com.example.productcatalogservice_june2025.services;

import com.example.productcatalogservice_june2025.models.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IProductService {
    Product getProductById(Long Id);
    Product createProduct(Product product);
    Product replaceProduct(Product product, Long id);
    List<Product> getAllProducts(); // This is a HW
    Product getProductsBasedOnUserScope(Long productId, Long userId);
}
