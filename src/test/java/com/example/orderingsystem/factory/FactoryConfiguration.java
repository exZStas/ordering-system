package com.example.orderingsystem.factory;

import com.example.orderingsystem.product.model.Product;
import com.example.orderingsystem.product_type.model.ProductType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfiguration {

    @Bean
    public ProductFactory getProductFactory() {
        return new ProductFactory();
    }

    @Bean
    public ProductTypeFactory getProductTypeFactory() {
        return new ProductTypeFactory();
    }
}
