package com.eshi.addis.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PricingConverter implements AttributeConverter<Pricing, Character> {

    @Override
    public Character convertToDatabaseColumn(Pricing x) {
        if (x == null) {
            return null;
        }
        return x.getScale();
    }

    @Override
    public Pricing convertToEntityAttribute(Character y) {
        if (y == null) {
            return null;
        }

        return Stream.of(Pricing.values())
                .filter(p -> p.getScale() == y)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}

