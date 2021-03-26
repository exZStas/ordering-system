package com.example.orderingsystem.product_type;

import com.example.orderingsystem.product_type.model.ProductType;
import com.example.orderingsystem.product_type.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.example.orderingsystem.product_type.model.ProductType.PRODUCT_TYPE_NAME_MAX_LENGTH;
import static java.lang.String.format;
import static java.util.Objects.nonNull;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public ProductType createProductType(String name, String description) {
        Assert.hasLength(name, "Product type name can't be null or empty");
        Assert.isTrue(name.length() <= PRODUCT_TYPE_NAME_MAX_LENGTH,
                format("Product name length can't exceeds %s chars", PRODUCT_TYPE_NAME_MAX_LENGTH));

        ProductType productType = new ProductType();
        productType.setName(name);
        productType.setDescription(description);

        return productTypeRepository.save(productType);
    }

    public ProductType getProductTypeById(Long productTypeId) {
        Assert.notNull(productTypeId, "Product type id can't be null");

        return productTypeRepository.findById(productTypeId).orElse(null);
    }
}
