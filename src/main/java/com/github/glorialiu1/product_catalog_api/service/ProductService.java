package com.github.glorialiu1.product_catalog_api.service;

import com.github.glorialiu1.product_catalog_api.dto.CreateProductRequest;
import com.github.glorialiu1.product_catalog_api.dto.ProductDetailsResponse;
import com.github.glorialiu1.product_catalog_api.dto.ProductListItemResponse;
import com.github.glorialiu1.product_catalog_api.entity.ProductType;
import com.github.glorialiu1.product_catalog_api.entity.Product;
import com.github.glorialiu1.product_catalog_api.entity.Colour;
import com.github.glorialiu1.product_catalog_api.repository.ProductTypeRepository;
import com.github.glorialiu1.product_catalog_api.repository.ProductRepository;
import com.github.glorialiu1.product_catalog_api.repository.ColourRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ColourRepository colourRepository;

    public ProductService(ProductRepository productRepository, ProductTypeRepository productTypeRepository, ColourRepository colourRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
        this.colourRepository = colourRepository;
    }

    // Transactional because productType is LAZY loaded. Staying in transaction avoids "lazy initialization" issues while mapping to DTOs.
    @Transactional
    public ProductListItemResponse create(CreateProductRequest req) {
        ProductType productType = productTypeRepository.findById(req.getProductTypeId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product type not found."));

        // Remove duplicates, keep order irrelevant.
        Set<Long> uniqueColourIds = new HashSet<>(req.getColourIds());
        List<Colour> colours = colourRepository.findAllById(uniqueColourIds);

        if (colours.size() != uniqueColourIds.size()) {
            // Find which IDs are missing (helpful error message)
            Set<Long> foundIds = colours.stream().map(Colour::getId).collect(java.util.stream.Collectors.toSet());
            Set<Long> missing = new HashSet<>(uniqueColourIds);
            missing.removeAll(foundIds);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Colour(s) not found: " + missing);
        }

        Product product = new Product(req.getName(), productType);
        product.getColours().addAll(colours);

        Product saved = productRepository.save(product);
        return new ProductListItemResponse(saved.getId(), saved.getName());
    }

    @Transactional(readOnly = true)
    public List<ProductListItemResponse> list() {
        return productRepository.findAll()
                .stream()
                .map(p -> new ProductListItemResponse(p.getId(), p.getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductDetailsResponse getDetails(Long id) {
        Product p = productRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        
        String productTypeName = p.getProductType().getName(); // Works inside @Transactional
        List<String> colourNames = p.getColours().stream().map(Colour::getName).toList();

        return new ProductDetailsResponse(p.getId(), p.getName(), productTypeName, colourNames);
    }
}
