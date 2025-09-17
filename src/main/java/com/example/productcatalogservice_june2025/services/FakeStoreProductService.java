package com.example.productcatalogservice_june2025.services;

import com.example.productcatalogservice_june2025.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{
    @Override
    public Product getProductById(Long Id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
