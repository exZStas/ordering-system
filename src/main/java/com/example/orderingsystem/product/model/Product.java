package com.example.orderingsystem.product.model;

import com.example.orderingsystem.constants.DatabaseConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.orderingsystem.constants.DatabaseConstants.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    public static final int PRODUCT_NAME_MAX_LENGTH = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORDERING_SYSTEM_SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = ORDERING_SYSTEM_SEQUENCE, sequenceName = ORDERING_SYSTEM_SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false, precision = 19)
    private Long id;

    @Column(name = "name", nullable = false, length = PRODUCT_NAME_MAX_LENGTH)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @Column(name = "product_type_id", nullable = false)
    private Long productTypeId;

}
