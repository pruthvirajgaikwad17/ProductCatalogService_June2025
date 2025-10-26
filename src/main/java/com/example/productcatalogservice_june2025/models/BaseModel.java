package com.example.productcatalogservice_june2025.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

// Serializable means in Java is the process of converting an objects state into a byte stream
// Deserialization is the reverse process, where the byte stream is used to reconstruct the original object in memory.
@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    private Long id;

    private Date createdAt;

    private Date lastUpdatedAt;

    private Status status;
}
