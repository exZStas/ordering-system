package com.example.orderingsystem.catalogue;

import com.example.orderingsystem.catalogue.model.Catalogue;
import com.example.orderingsystem.catalogue.repository.CatalogueRepository;
import com.example.orderingsystem.catalogue_product.CatalogueProductService;
import com.example.orderingsystem.catalogue_product.repository.CatalogueProductRepository;
import com.example.orderingsystem.product.ProductService;
import com.example.orderingsystem.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import static java.util.Objects.nonNull;

@Service
public class CatalogueService {

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Autowired
    private CatalogueProductService catalogueProductService;

    public Catalogue getCatalogueById(Long catalogueId) {
        Assert.notNull(catalogueId, "Catalogue id can't be null");

        return catalogueRepository.findById(catalogueId).orElse(null);
    }

    public Catalogue createCatalogue(String name) {
        Assert.hasLength(name, "Catalogue id can't be null or empty");

        Catalogue catalogue = new Catalogue();
        catalogue.setName(name);

        return catalogueRepository.save(catalogue);
    }

    public void addProductToCatalogue(Long catalogueId, Long productId) {
        catalogueProductService.createCatalogueProduct(catalogueId, productId);
    }

    public void removeProductFromCatalogue(Long catalogueId, Long productId) {
        catalogueProductService.removeCatalogueProduct(catalogueId, productId);
    }
}
