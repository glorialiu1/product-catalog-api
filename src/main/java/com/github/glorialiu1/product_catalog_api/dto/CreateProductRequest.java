package com.github.glorialiu1.product_catalog_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateProductRequest {
    
    @NotBlank
    private String name;

    @NotNull
    private Long productTypeId;

    @NotEmpty
    private List<@NotNull Long> colourIds;

    public CreateProductRequest() {

    }

    public String getName() {
        return name;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public List<Long> getColourIds() {
        return colourIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public void setColourIds(List<Long> colourIds) {
        this.colourIds = colourIds;
    }
}
