package com.example.orderingsystem.catalogue.repository;

import com.example.orderingsystem.catalogue.model.Catalogue;
import com.example.orderingsystem.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
}
