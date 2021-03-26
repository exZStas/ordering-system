package com.example.orderingsystem.factory;

import com.example.orderingsystem.product_type.model.ProductType;
import com.example.orderingsystem.product_type.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTypeFactory {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public ProductType createProductType(String name, String description) {
        ProductType productType = new ProductType();
        productType.setName(name);
        productType.setDescription(description);

        return productTypeRepository.save(productType);
    }
}
