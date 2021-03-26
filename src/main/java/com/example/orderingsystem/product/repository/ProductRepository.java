package com.example.orderingsystem.product.repository;

import com.example.orderingsystem.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
