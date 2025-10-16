package com.example.productcatalogservice_june2025.services;

import com.example.productcatalogservice_june2025.dtos.SearchRequestDto;
import com.example.productcatalogservice_june2025.dtos.SortParams;
import com.example.productcatalogservice_june2025.dtos.SortType;
import com.example.productcatalogservice_june2025.models.Product;
import com.example.productcatalogservice_june2025.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepo productRepo;

    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber, List<SortParams> sortParams) {
//        Sort sortById = Sort.by("id").descending();
//        Sort sort = Sort.by( "price").ascending().and(sortById); // id will be checking by default in asc if price is same
        Sort sort = null;
        if (!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType() == SortType.ASC) {
                sort = sort.by(sortParams.get(0).getSortCriteria()).ascending();
            } else {
                sort = sort.by(sortParams.get(0).getSortCriteria()).descending();
            }
            for(int i = 1; i <  sortParams.size(); i++) {
                if(sortParams.get(i).getSortType() == SortType.ASC) {
                    sort = sort.and(sort.by(sortParams.get(i).getSortCriteria()).ascending());
                } else {
                    sort = sort.and(sort.by(sortParams.get(i).getSortCriteria()).descending());
                }
            }
        }
        return productRepo.findByNameEquals(query, PageRequest.of(pageNumber, pageSize,  sort));
    }
}
