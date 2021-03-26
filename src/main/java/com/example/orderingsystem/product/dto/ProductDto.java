package com.example.orderingsystem.product.dto;

import com.example.orderingsystem.product_type.dto.ProductTypeDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class ProductDto {

    Long id;

    @NotBlank
    @Size(max = 50)
    String name;

    @Size(max = 250)
    String description;

    @NotNull
    @Min(0)
    Long stock;

    @Valid
    ProductTypeDto productTypeDto;


}
