package com.example.productcatalogservice_june2025.dtos;

import com.example.productcatalogservice_june2025.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private double price;

    private String imageUrl;

    private CategoryDto categoryDto;
}
