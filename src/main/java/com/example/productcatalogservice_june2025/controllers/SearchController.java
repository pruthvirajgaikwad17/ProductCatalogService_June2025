package com.example.productcatalogservice_june2025.controllers;

import com.example.productcatalogservice_june2025.dtos.SearchRequestDto;
import com.example.productcatalogservice_june2025.models.Product;
import com.example.productcatalogservice_june2025.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageSize(), searchRequestDto.getPageNumber(), searchRequestDto.getSortParams());
    }

    /*
  Payload

  {
  "query" : "iphone",
   "pageSize" : 8,
   "pageNumber" : 0,
   "sortParams" : [
    {
       "sortCriteria" : "price",
       "sortType" : "DESC"
    },
    {
       "sortCriteria" : "id",
       "sortType" : "DESC"
    }
   ]
}
 */
}
