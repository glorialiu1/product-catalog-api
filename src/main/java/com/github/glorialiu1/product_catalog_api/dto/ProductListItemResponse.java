package com.github.glorialiu1.product_catalog_api.dto;

public class ProductListItemResponse {
    
    private Long id;
    private String name;

    public ProductListItemResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
