package com.example.orderingsystem.order.dto;

import com.example.orderingsystem.constants.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

    Long id;
    String name;
    String description;
    OrderStatus orderStatus;
    Long productId;
}
