package com.example.orderingsystem.product_type.repository;

import com.example.orderingsystem.product_type.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
