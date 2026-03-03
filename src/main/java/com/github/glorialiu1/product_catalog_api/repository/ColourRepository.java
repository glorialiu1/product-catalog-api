package com.github.glorialiu1.product_catalog_api.repository;

import com.github.glorialiu1.product_catalog_api.entity.Colour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColourRepository extends JpaRepository<Colour, Long>{
    
}
