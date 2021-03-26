package com.example.orderingsystem.catalogue_product.repository;

import com.example.orderingsystem.catalogue.repository.CatalogueRepository;
import com.example.orderingsystem.catalogue_product.model.CatalogueProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueProductRepository extends JpaRepository<CatalogueProduct, Long> {

    CatalogueProduct findCatalogueProductByCatalogueIdAndProductId(Long catalogueId, Long productId);
}
