package com.example.productcatalogservice_june2025.repos;

import com.example.productcatalogservice_june2025.models.Category;
import com.example.productcatalogservice_june2025.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest  // Bootstraps Spring context
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;  // Now Spring injects it

    @Test
    @Transactional
    void testLoading() {
        Optional<Category> categoryOptional = categoryRepo.findById(1L);
             for (Product p : categoryOptional.get().getProducts()) {
                System.out.println(p);
            }
    }
}
