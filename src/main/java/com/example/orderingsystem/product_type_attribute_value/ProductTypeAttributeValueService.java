package com.example.orderingsystem.product_type_attribute_value;

import com.example.orderingsystem.attribute_value.AttributeValueService;
import com.example.orderingsystem.attribute_value.model.AttributeValue;
import com.example.orderingsystem.product_type.ProductTypeService;
import com.example.orderingsystem.product_type.model.ProductType;
import com.example.orderingsystem.product_type_attribute_value.model.ProductTypeAttributeValue;
import com.example.orderingsystem.product_type_attribute_value.repository.ProductTypeAttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import static java.lang.String.format;
import static java.util.Objects.nonNull;

@Service
public class ProductTypeAttributeValueService {

    @Autowired
    private ProductTypeAttributeValueRepository productTypeAttributeValueRepository;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private AttributeValueService attributeValueService;

    public ProductTypeAttributeValue getProductTypeAttributeValueByProductTypeIdAndAttributeValue(Long productTypeId, Long attributeValueId) {
        Assert.notNull(productTypeId, "Product type id can't be null");
        Assert.notNull(attributeValueId, "Attribute value id can't be null");

        return productTypeAttributeValueRepository.findProductTypeAttributeValueByProductTypeIdAndAttributeValueId(productTypeId, attributeValueId);
    }

    public ProductTypeAttributeValue createProductTypeAttributeValue(Long productTypeId, Long attributeValueId) {
        Assert.notNull(productTypeId, "Product type id can't be null");
        Assert.notNull(attributeValueId, "Attribute value id can't be null");

        ProductType productType = productTypeService.getProductTypeById(productTypeId);
        Assert.notNull(productType, format("Can't find product type by id: [%s]", productTypeId));

        AttributeValue attributeValue = attributeValueService.getAttributeValueById(attributeValueId);
        Assert.notNull(attributeValue, format("Can't find attribute value by id: [%s]", attributeValueId));

        ProductTypeAttributeValue productTypeAttributeValue =
                getProductTypeAttributeValueByProductTypeIdAndAttributeValue(productTypeId, attributeValueId);

        if(nonNull(productTypeAttributeValue)) {
            return productTypeAttributeValue;
        }

        ProductTypeAttributeValue productTypeAttributeValueToCreate = new ProductTypeAttributeValue();
        productTypeAttributeValueToCreate.setAttributeValueId(attributeValueId);
        productTypeAttributeValueToCreate.setProductTypeId(productTypeId);

        return productTypeAttributeValueRepository.save(productTypeAttributeValueToCreate);
    }
}
