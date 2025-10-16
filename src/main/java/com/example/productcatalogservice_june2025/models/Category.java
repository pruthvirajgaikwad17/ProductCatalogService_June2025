package com.example.productcatalogservice_june2025.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;

    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size=100) // If we SELECT in fetch mode that time we will use this to reduce the number of queries
    // N+1 issue is resolved using SELECT or we can go with SUBSELECT instead of batch size
    @JsonBackReference
    private List<Product> products;

}
