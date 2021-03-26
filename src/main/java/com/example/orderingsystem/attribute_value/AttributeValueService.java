package com.example.orderingsystem.attribute_value;

import com.example.orderingsystem.attribute.AttributeService;
import com.example.orderingsystem.attribute.model.Attribute;
import com.example.orderingsystem.attribute_value.model.AttributeValue;
import com.example.orderingsystem.attribute_value.repository.AttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static java.lang.String.format;

@Service
public class AttributeValueService {

    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @Autowired
    private AttributeService attributeService;

    public AttributeValue addAttributeValue(String name, String description, Long value, Long attributeId) {
        //todo better enrich validation with name and description validation to have self-sufficient service methods
        // skipped that intentionally to save time
        Assert.hasLength(name, "Attribute value name can't be null or empty");
        Assert.notNull(attributeId, "Attribute id can't be null");

        Attribute attribute = attributeService.getAttributeById(attributeId);
        Assert.notNull(attribute, format("Can't find attribute by id: [%s]", attributeId));

        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setName(name);
        attributeValue.setDescription(description);
        attributeValue.setAttributeId(attributeId);
        attributeValue.setValue(value);

        return attributeValueRepository.save(attributeValue);
    }

    public AttributeValue getAttributeValueById(Long attributeValueId) {
        Assert.notNull(attributeValueId, "Attribute value id can't be null");

        return attributeValueRepository.findById(attributeValueId).orElse(null);
    }
}
