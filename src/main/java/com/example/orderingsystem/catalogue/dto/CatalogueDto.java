package com.example.orderingsystem.catalogue.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CatalogueDto {

    private Long id;

    @NotBlank(message = "Catalogue's name can't be empty")
    @Size(max = 50)
    private String name;

}
