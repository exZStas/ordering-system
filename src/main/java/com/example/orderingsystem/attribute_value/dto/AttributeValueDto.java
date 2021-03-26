package com.example.orderingsystem.attribute_value.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class AttributeValueDto {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 250)
    private String description;
    private Long value;
    private Long attributeId;
}
