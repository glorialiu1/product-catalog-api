package com.github.glorialiu1.product_catalog_api.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Auto generate ID for this entity.
    private Long id;

    @Column(nullable = false)       // name is required.
    private String name;

    // Multiple products can share one product type.
    // One Product --> One ProductType
    // One ProductType --> Many Products
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    // A product can have multiple colours and a colour can belong to multiple products.
    // One Product --> Many Colours
    // One Colour --> Many Products
    @ManyToMany
    @JoinTable(
        name = "product_colour",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "colour_id")
    )
    private Set<Colour> colours = new HashSet<>();      // Set to avoid duplicate colours.

    public Product() {

    }

    public Product(String name, ProductType productType) {
        this.name = name;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Set<Colour> getColours() {
        return colours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
