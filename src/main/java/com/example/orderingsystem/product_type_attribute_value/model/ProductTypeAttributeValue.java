package com.example.orderingsystem.product_type_attribute_value.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.orderingsystem.constants.DatabaseConstants.ORDERING_SYSTEM_SEQUENCE;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PRODUCT_TYPE_ATTRIBUTE_VALUE")
public class ProductTypeAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORDERING_SYSTEM_SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = ORDERING_SYSTEM_SEQUENCE, sequenceName = ORDERING_SYSTEM_SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false, precision = 19)
    private Long id;

    @Column(name = "product_type_id", nullable = false)
    private Long productTypeId;

    @Column(name = "attribute_value_id", nullable = false)
    private Long attributeValueId;
}
