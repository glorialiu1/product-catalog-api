package com.github.glorialiu1.product_catalog_api;

import com.github.glorialiu1.product_catalog_api.entity.ProductType;
import com.github.glorialiu1.product_catalog_api.entity.Colour;
import com.github.glorialiu1.product_catalog_api.repository.ProductTypeRepository;
import com.github.glorialiu1.product_catalog_api.repository.ColourRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{
    
    private final ProductTypeRepository productTypeRepository;
    private final ColourRepository colourRepository;

    public DataLoader(ProductTypeRepository productTypeRepository, ColourRepository colourRepository) {
        this.productTypeRepository = productTypeRepository;
        this.colourRepository = colourRepository;
    }

    @Override
    public void run(String... args) {
        // seed product types

        if (productTypeRepository.count() == 0) {
            productTypeRepository.save(new ProductType("Sofa"));
            productTypeRepository.save(new ProductType("Chair"));
            productTypeRepository.save(new ProductType("Table"));
            productTypeRepository.save(new ProductType("Bed"));
        }

        // seed colours
        if (colourRepository.count() == 0) {
            colourRepository.save(new Colour("Blue"));
            colourRepository.save(new Colour("Ruby"));
            colourRepository.save(new Colour("Black"));
            colourRepository.save(new Colour("White"));
        }
    }
}
