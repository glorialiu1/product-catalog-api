package com.github.glorialiu1.product_catalog_api.controller;

import com.github.glorialiu1.product_catalog_api.dto.CreateProductRequest;
import com.github.glorialiu1.product_catalog_api.dto.ProductDetailsResponse;
import com.github.glorialiu1.product_catalog_api.dto.ProductListItemResponse;
import com.github.glorialiu1.product_catalog_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add new product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductListItemResponse create(@Valid @RequestBody CreateProductRequest req) {
        return productService.create(req);
    }

    // List products (id, name)
    @GetMapping
    public List<ProductListItemResponse> list() {
        return productService.list();
    }

    // Product details (id, name, productType, colours)
    @GetMapping("/{id}")
    public ProductDetailsResponse details(@PathVariable Long id) {
        return productService.getDetails(id);
    }
    
}
