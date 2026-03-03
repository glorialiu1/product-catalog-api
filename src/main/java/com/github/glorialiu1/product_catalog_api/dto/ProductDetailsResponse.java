package com.github.glorialiu1.product_catalog_api.dto;

import java.util.List;

public class ProductDetailsResponse {
    
    private Long id;
    private String name;
    private String productType;
    private List<String> colours;

    public ProductDetailsResponse(Long id, String name, String productType, List<String> colours) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.colours = colours;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProductType() {
        return productType;
    }

    public List<String> getColours() {
        return colours;
    }
}
