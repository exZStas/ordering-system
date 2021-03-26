package com.example.orderingsystem.product_type.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.orderingsystem.constants.DatabaseConstants.ORDERING_SYSTEM_SEQUENCE;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PRODUCT_TYPE")
public class ProductType {

    public static final int PRODUCT_TYPE_NAME_MAX_LENGTH = 50;// todo extract to database constants?

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORDERING_SYSTEM_SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = ORDERING_SYSTEM_SEQUENCE, sequenceName = ORDERING_SYSTEM_SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false, precision = 19)
    private Long id;

    @Column(name = "name", nullable = false, length = PRODUCT_TYPE_NAME_MAX_LENGTH)
    private String name;

    @Column(name = "description")
    private String description;
}
