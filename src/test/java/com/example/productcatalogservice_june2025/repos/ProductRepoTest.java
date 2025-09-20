package com.example.productcatalogservice_june2025.repos;

import com.example.productcatalogservice_june2025.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void testQueries() {
//        List<Product> products = productRepo.getMeNameOfMyFavouriteProductById(2L);
//        for(Product p: products){
//            System.out.println(p.getPrice());
//        }
        System.out.println(productRepo.getMeNameOfMyFavouriteProductById(1L));
    }

}