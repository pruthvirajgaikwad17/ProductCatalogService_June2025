package com.example.productcatalogservice_june2025.controllers;

import com.example.productcatalogservice_june2025.dtos.CategoryDto;
import com.example.productcatalogservice_june2025.dtos.ProductDto;
import com.example.productcatalogservice_june2025.models.Category;
import com.example.productcatalogservice_june2025.models.Product;
import com.example.productcatalogservice_june2025.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
//    @Qualifier("storageProductService")
    IProductService productService;

//    @Autowired
//    @Qualifier("fakeStoreProductService")
//    IProductService productService2;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        // Dummy response
        List<Product> products = productService.getAllProducts();
        if(products.isEmpty())  return new ArrayList<>();
        List<ProductDto> productDtos = products.stream()
                .map(this::from)   // convert each DTO to Product
                .toList();
        return productDtos;
    }

    // Read for @PathVariable, @RequestParam, @QueryParam
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {

            if (productId < 0) {
                throw new IllegalArgumentException("Product Id not found");
            } else if (productId == 0) {
                throw new IllegalArgumentException("Product exist with positive id (Zero)");
            }
            Product product = productService.getProductById(productId);
            if (product == null) return null;

            ProductDto productDto = from(product);
            return new ResponseEntity<>(productDto, HttpStatus.OK);

    }

    @PostMapping("products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.createProduct(product);
        if(output == null) return null;
        return from(output);
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.replaceProduct(product, id);
        if(output == null) return null;
        return from(output);
    }

    @GetMapping("/products/{productId}/{userId}")
    public ProductDto getProductDetailsBasedOnUserScope(@PathVariable Long productId, @PathVariable Long userId) {
        return from(productService.getProductsBasedOnUserScope(productId, userId));
    }

    private Product  from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        if(productDto.getCategory() != null) {
            Category  category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory()!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            product.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
