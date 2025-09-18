package com.example.productcatalogservice_june2025.repos;

import com.example.productcatalogservice_june2025.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
