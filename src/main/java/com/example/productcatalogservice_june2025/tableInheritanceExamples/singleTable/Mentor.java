package com.example.productcatalogservice_june2025.tableInheritanceExamples.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_mentor")
@DiscriminatorValue(value = "100")
public class Mentor extends User {
    private Long numberOfHours;
}
