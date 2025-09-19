package com.example.productcatalogservice_june2025.tableInheritanceExamples.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_ta")
@DiscriminatorValue(value = "10")
public class Ta extends User {
    private Long numberOfRating;
}
