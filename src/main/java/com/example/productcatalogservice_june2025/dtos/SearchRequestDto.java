package com.example.productcatalogservice_june2025.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SearchRequestDto {
    private String query;
    private Integer pageNumber;
    private Integer pageSize;
    List<SortParams> sortParams = new ArrayList<>();
}
