package com.example.orderingsystem.catalogue;

import com.example.orderingsystem.catalogue.dto.CatalogueDto;
import com.example.orderingsystem.catalogue.dto.CatalogueDtoAssembler;
import com.example.orderingsystem.catalogue.model.Catalogue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Objects;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/catalogues")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addCatalogue(@RequestBody @Validated CatalogueDto catalogueRequestBody) {
        String catalogueName = catalogueRequestBody.getName();

        Catalogue catalogue = catalogueService.createCatalogue(catalogueName);
        CatalogueDto catalogueDto = CatalogueDtoAssembler.mapEntityToDto(catalogue);

        return new ResponseEntity(catalogueDto, OK);
    }

    @GetMapping("{catalogueId}")
    public ResponseEntity getCatalogue(@PathVariable("catalogueId") Long catalogueId) {
        Catalogue catalogue = catalogueService.getCatalogueById(catalogueId);

        if(isNull(catalogue)) {
            return new ResponseEntity(NOT_FOUND);
        }

        CatalogueDto catalogueDto = CatalogueDtoAssembler.mapEntityToDto(catalogue);
        return new ResponseEntity(catalogueDto, OK);
    }

    @PostMapping("/{catalogueId}/products/{productId}")
    public ResponseEntity addProductToCatalogue(@PathVariable("catalogueId") Long catalogueId,
                                                @PathVariable("productId") Long productId) {
        catalogueService.addProductToCatalogue(catalogueId, productId);

        return new ResponseEntity(OK);
    }

    @DeleteMapping("/{catalogueId}/products/{productId}")
    public ResponseEntity removeProductFromCatalogue(@PathVariable("catalogueId") Long catalogueId,
                                                     @PathVariable("productId") Long productId) {
        catalogueService.removeProductFromCatalogue(catalogueId, productId);

        return new ResponseEntity(OK);
    }

}
