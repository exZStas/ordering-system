package com.example.orderingsystem.order.model;

import com.example.orderingsystem.constants.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

import static com.example.orderingsystem.constants.DatabaseConstants.ORDERING_SYSTEM_SEQUENCE;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USER_ORDER")
public class Order {

    public static final int ORDER_NAME_MAX_LENGTH = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORDERING_SYSTEM_SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = ORDERING_SYSTEM_SEQUENCE, sequenceName = ORDERING_SYSTEM_SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false, precision = 19)
    private Long id;

    @Column(name = "name", nullable = false, length = ORDER_NAME_MAX_LENGTH)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "creation_date")
    private Date creationDate;
}
