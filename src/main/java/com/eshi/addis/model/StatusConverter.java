package com.eshi.addis.model;

import com.eshi.addis.model.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Character> {
    @Override
    public Character convertToDatabaseColumn(Status status) {
        if (status==null) {
            return null;
        }
        return status.getStatus();
    }

    @Override
    public Status convertToEntityAttribute(Character character) {
        if (character==null) {
            return null;
        }
        return Stream.of(Status.values())
                .filter(c -> c.getStatus()==character)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
