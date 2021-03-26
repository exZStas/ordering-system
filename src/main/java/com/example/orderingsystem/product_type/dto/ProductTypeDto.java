package com.example.orderingsystem.product_type.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProductTypeDto {

    Long id;

    @NotBlank
    @Size(max = 50)
    String name;

    @Size(max = 250)
    String description;
}
