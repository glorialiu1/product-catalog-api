package com.github.glorialiu1.product_catalog_api.repository;

import com.github.glorialiu1.product_catalog_api.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{
    
}
