package com.example.orderingsystem.attribute.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class AttributeDto {
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 250)
    private String description;
}
