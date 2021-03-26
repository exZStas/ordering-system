package com.example.orderingsystem.attribute;

import com.example.orderingsystem.attribute.model.Attribute;
import com.example.orderingsystem.attribute.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    public Attribute createAttribute(String name, String description) {
        //todo better enrich validation with name and description validation to have self-sufficient service methods
        // skipped that intentionally to save time
        Assert.hasLength(name, "Attribute name can't be null or empty");

        Attribute attribute = new Attribute();
        attribute.setName(name);
        attribute.setDescription(description);

        return attributeRepository.save(attribute);
    }

    public Attribute getAttributeById(Long attributeId) {
        Assert.notNull(attributeId, "Attribute id can't be null");

        return attributeRepository.findById(attributeId).orElse(null);
    }
}
