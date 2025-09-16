package com.example.productcatalogservice_june2025.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;

    private String description;

    private double price;

    private String imageUrl;

    private Category category;


}
