package com.example.orderingsystem.attribute.repository;

import com.example.orderingsystem.attribute.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
