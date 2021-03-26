package com.example.orderingsystem.catalogue_product;

import com.example.orderingsystem.catalogue.CatalogueService;
import com.example.orderingsystem.catalogue.model.Catalogue;
import com.example.orderingsystem.catalogue_product.model.CatalogueProduct;
import com.example.orderingsystem.catalogue_product.repository.CatalogueProductRepository;
import com.example.orderingsystem.product.ProductService;
import com.example.orderingsystem.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

import static java.util.Objects.nonNull;

@Service
public class CatalogueProductService {

    @Autowired
    private ProductService productService;

    @Autowired
    private CatalogueService catalogueService;

    @Autowired
    private CatalogueProductRepository catalogueProductRepository;

    public CatalogueProduct createCatalogueProduct(Long catalogueId, Long productId) {
        Assert.notNull(catalogueId, "Catalogue id can't be null");
        Assert.notNull(productId, "ProductId id can't be null");

        Catalogue catalogue = catalogueService.getCatalogueById(catalogueId);
        Assert.notNull(catalogue, "Catalogue can't be null");

        Product product = productService.getProductById(productId);
        Assert.notNull(product, "Product can't be null");

        CatalogueProduct catalogueProduct = catalogueProductRepository.findCatalogueProductByCatalogueIdAndProductId(catalogueId, productId);
        //Assert.isNull(catalogueProduct, String.format("Catalog with id: [%s] already contains product with id: [%s]", catalogueId, productId));
        if(nonNull(catalogueProduct)) {
            return catalogueProduct;
        }

        CatalogueProduct catalogueProduct2create = new CatalogueProduct();
        catalogueProduct2create.setCatalogueId(catalogueId);
        catalogueProduct2create.setProductId(productId);

        return catalogueProductRepository.save(catalogueProduct2create);
    }

    public void removeCatalogueProduct(Long catalogueId, Long productId) {
        Assert.notNull(catalogueId, "Catalogue id can't be null");
        Assert.notNull(productId, "ProductId id can't be null");

        CatalogueProduct catalogueProduct = catalogueProductRepository.findCatalogueProductByCatalogueIdAndProductId(catalogueId, productId);
        Assert.notNull(catalogueProduct, String.format("Nothing to remove. Couldn't find product catalog with catalog id: [%s] and product id: [%s].", catalogueId, productId));

        catalogueProductRepository.delete(catalogueProduct);
    }
}
