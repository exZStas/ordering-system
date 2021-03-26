package com.example.orderingsystem.catalogue.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.orderingsystem.constants.DatabaseConstants.ORDERING_SYSTEM_SEQUENCE;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CATALOGUE")
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORDERING_SYSTEM_SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = ORDERING_SYSTEM_SEQUENCE, sequenceName = ORDERING_SYSTEM_SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false, precision = 19)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
