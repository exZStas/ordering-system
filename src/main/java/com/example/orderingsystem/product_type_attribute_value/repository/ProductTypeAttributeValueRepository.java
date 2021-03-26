package com.example.orderingsystem.product_type_attribute_value.repository;

import com.example.orderingsystem.product_type_attribute_value.model.ProductTypeAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeAttributeValueRepository extends JpaRepository<ProductTypeAttributeValue, Long> {

    ProductTypeAttributeValue findProductTypeAttributeValueByProductTypeIdAndAttributeValueId(Long productTypeId, Long attributeValueId);
}
