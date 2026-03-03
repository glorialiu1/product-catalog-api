package com.github.glorialiu1.product_catalog_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_type")
public class ProductType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate ID for this entity.
    private Long id;

    @Column(nullable = false, unique = true)        // name is required and unique.
    private String name;

    public ProductType() {

    }

    public ProductType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
