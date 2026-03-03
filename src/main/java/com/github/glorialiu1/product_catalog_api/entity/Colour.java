package com.github.glorialiu1.product_catalog_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "colour")
public class Colour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Auto generates ID for this entity.
    private Long id;

    @Column(nullable = false, unique = true)        // name is required and unique.
    private String name;

    public Colour() {

    }

    public Colour(String name) {
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
