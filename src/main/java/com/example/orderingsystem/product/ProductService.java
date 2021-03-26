package com.example.orderingsystem.product;

import com.example.orderingsystem.product.model.Product;
import com.example.orderingsystem.product.repository.ProductRepository;
import com.example.orderingsystem.product_type.ProductTypeService;
import com.example.orderingsystem.product_type.model.ProductType;
import com.example.orderingsystem.product_type.repository.ProductTypeRepository;
import com.example.orderingsystem.product_type_attribute_value.ProductTypeAttributeValueService;
import com.example.orderingsystem.product_type_attribute_value.model.ProductTypeAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

import static com.example.orderingsystem.product.model.Product.PRODUCT_NAME_MAX_LENGTH;
import static com.example.orderingsystem.product_type.model.ProductType.PRODUCT_TYPE_NAME_MAX_LENGTH;
import static java.lang.String.format;
import static java.util.Objects.nonNull;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductTypeAttributeValueService productTypeAttributeValueService;

    public Product createProduct(String name, String description, Long stock, Long productTypeId) {
        Assert.hasLength(name, "Product name can't be null or empty");
        Assert.isTrue(stock > 0, "Product's stock can't be null or less than zero");
        Assert.notNull(productTypeId, "Product type id can't be null");
        Assert.isTrue(name.length() <= PRODUCT_NAME_MAX_LENGTH,
                format("Product name length can't exceeds %s chars", PRODUCT_NAME_MAX_LENGTH));

        ProductType productType = productTypeService.getProductTypeById(productTypeId);
        Assert.notNull(productType, format("Can't find product type by id: [%s]", productTypeId));

        Product product = new Product();
        //todo unique index on product's name to avoid duplication
        product.setName(name);
        product.setDescription(description);
        product.setStock(stock);
        product.setProductTypeId(productTypeId);

        return productRepository.save(product);
    }

    public Product createProduct(String productName, String productDescription, Long stock,
                                 String productTypeName, String productTypeDescription) {
        //todo product types can be shared. Better use unique index name on product type name and look first if such already exists
        ProductType productType = productTypeService.createProductType(productTypeName, productTypeDescription);
        Assert.notNull(productType, "Product type can't be null");

        return createProduct(productName, productDescription, stock, productType.getId());

    }

    public Product getProductById(Long productId) {
        Assert.notNull(productId, "Product id can't be null");

        return productRepository.findById(productId).orElse(null);
    }


    // todo better use distributed locks
    //  So that we are rest assured this method can't be run in parallel from multiple JVMs
    public Long stockProduct(Long productId, Long stock) {
        Assert.isTrue(stock > 0, "Product's stock can't be null or less than zero");

        Product product = getProductById(productId);
        Assert.notNull(product, format("Can't find product by id: [%s]", productId));

        Long currentProductStock = product.getStock();

        product.setStock(currentProductStock + stock);

        return productRepository.save(product).getStock();
    }

    // todo better use distributed locks
    //  So that we are rest assured this method can't be run in parallel from multiple JVMs
    // for simplicity let's imagine user can order only single instance of product
    public void withdrawProduct(Long productId) {
        Assert.notNull(productId, "Product id can't be null");

        Product product = getProductById(productId);
        Assert.notNull(product, format("Can't find product by id: [%s]", productId));

        Long currentProductStock = product.getStock();
        product.setStock(currentProductStock - 1L);

        productRepository.save(product);
    }

    public void addAttributeValuesToProduct(Long productId, Long attributeValueId) {
        Assert.notNull(productId, "Product id can't be null");
        Assert.notNull(attributeValueId, "Attribute value id can't be null");

        Product product = getProductById(productId);
        Assert.notNull(product, format("Can't find product by id: [%s]", productId));

        Long productTypeId = product.getProductTypeId();
        productTypeAttributeValueService.createProductTypeAttributeValue(productTypeId, attributeValueId);
    }

}
