package com.example.orderingsystem;

import com.example.orderingsystem.factory.ProductFactory;
import com.example.orderingsystem.factory.ProductTypeFactory;
import com.example.orderingsystem.product.ProductService;
import com.example.orderingsystem.product.model.Product;
import com.example.orderingsystem.product.repository.ProductRepository;
import com.example.orderingsystem.product_type.model.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderingSystemApplicationTests {

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductTypeFactory productTypeFactory;

    @Autowired
    private ProductRepository productRepository;

    private Product product_1;
    private ProductType productType_1;

    @BeforeEach
    public void setUp() {
        productType_1 = productTypeFactory.createProductType("my prod type", "my desc");
        product_1 = productFactory.createProduct("my prod", "my desc", 1L, productType_1.getId());
    }

    @Test
    void testGetProductById() {
        //given
        //when
        Product productActual = productRepository.findById(product_1.getId()).orElse(null);

        //then
        assertNotNull(productActual);
        assertEquals(product_1.getId(), productActual.getId());
    }

}
