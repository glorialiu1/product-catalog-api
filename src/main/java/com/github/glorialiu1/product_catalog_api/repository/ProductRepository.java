package com.github.glorialiu1.product_catalog_api.repository;

import com.github.glorialiu1.product_catalog_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
