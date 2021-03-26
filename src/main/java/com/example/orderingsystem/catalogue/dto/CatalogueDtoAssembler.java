package com.example.orderingsystem.catalogue.dto;

import com.example.orderingsystem.catalogue.model.Catalogue;

import static java.util.Objects.isNull;

public class CatalogueDtoAssembler {

    public static CatalogueDto mapEntityToDto(Catalogue entity) {

        if(isNull(entity)) {
            return null;
        }

        return CatalogueDto.builder()
                .id(entity.getId())
                .name(entity.getName()).build();
    }
}
