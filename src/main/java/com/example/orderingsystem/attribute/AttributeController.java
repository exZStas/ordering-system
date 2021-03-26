package com.example.orderingsystem.attribute;

import com.example.orderingsystem.attribute.dto.AttributeDto;
import com.example.orderingsystem.attribute.model.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/attributes")
@RestController
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @PostMapping
    public ResponseEntity addAttribute(@RequestBody @Validated AttributeDto attributeDto) {
        Attribute attribute = attributeService.createAttribute(attributeDto.getName(), attributeDto.getDescription());
        // todo convert to dto and return
        return new ResponseEntity(attribute, HttpStatus.OK);
    }
}
