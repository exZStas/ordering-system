package com.example.orderingsystem.attribute_value;

import com.example.orderingsystem.attribute_value.dto.AttributeValueDto;
import com.example.orderingsystem.attribute_value.model.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/attributes-values")
@RestController
public class AttributeValueController {

    @Autowired
    private AttributeValueService attributeValueService;

    @PostMapping
    public ResponseEntity addAttributeValue(@RequestBody @Validated AttributeValueDto attributeValueDto) {
        AttributeValue attributeValue = attributeValueService.addAttributeValue(attributeValueDto.getName(), attributeValueDto.getDescription(),
                attributeValueDto.getValue(), attributeValueDto.getAttributeId());

        return new ResponseEntity(attributeValue, HttpStatus.OK);
    }
}
