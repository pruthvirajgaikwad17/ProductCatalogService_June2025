package com.example.productcatalogservice_june2025.controllers;

import com.example.productcatalogservice_june2025.dtos.ProductDto;
import com.example.productcatalogservice_june2025.models.Product;
import com.example.productcatalogservice_june2025.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProducts_RunSuccessfully() throws Exception {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Iphone24");
        List<Product> products  = new ArrayList<>();
        products.add(product1);
        when(productService.getAllProducts()).thenReturn(products);

        ProductDto productDto = new ProductDto();
        productDto.setName("Iphone24");
        productDto.setId(1L);
        List<ProductDto> productDtos  = new ArrayList<>();
        productDtos.add(productDto);

        String response = objectMapper.writeValueAsString(productDtos);
        System.out.println(response);
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
        // response body == responsestring
    }

}
