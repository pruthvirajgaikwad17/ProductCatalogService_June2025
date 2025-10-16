package com.example.productcatalogservice_june2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SortParams {
    private String sortCriteria;
    private SortType sortType;
}
