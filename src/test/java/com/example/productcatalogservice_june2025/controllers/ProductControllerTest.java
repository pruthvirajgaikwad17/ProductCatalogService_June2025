package com.example.productcatalogservice_june2025.controllers;

import com.example.productcatalogservice_june2025.dtos.ProductDto;
import com.example.productcatalogservice_june2025.models.Product;
import com.example.productcatalogservice_june2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void testGetProductById_WithValidId_RunSuccessfully() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone17");

        when(productService.getProductById(productId)).thenReturn(product);

        // Act
        ResponseEntity<ProductDto> productDtoResponseEntity =
                productController.getProductById(1L);

        // Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody().getId());
        assertEquals(productId, productDtoResponseEntity.getBody().getId());
        assertEquals("Iphone17", productDtoResponseEntity.getBody().getName());
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    public void testGetProductById_WithNegativeId_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> productController.getProductById(-1L));
        assertEquals("Product Id not found", exception.getMessage());
        verify(productService, times(0)).getProductById(-1L);
    }

    @Test
    public void testGetProductById_WithZeroId_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> productController.getProductById(0L));
        assertEquals("Product exist with positive id (Zero)", exception.getMessage());
        verify(productService, times(0)).getProductById(0L);
    }
}