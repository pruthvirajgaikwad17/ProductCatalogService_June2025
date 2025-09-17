package com.example.productcatalogservice_june2025.dtos;

import com.example.productcatalogservice_june2025.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CategoryDto {

    private Long id; // ToDo: Why are we taking as input

    private String name;

    private String description;
}
