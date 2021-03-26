package com.example.orderingsystem.factory;

import com.example.orderingsystem.product.model.Product;
import com.example.orderingsystem.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductFactory {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(String name, String description, Long stock, Long productTypeId) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setStock(stock);
        product.setProductTypeId(productTypeId);

        return productRepository.save(product);
    }
}
