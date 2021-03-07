package com.enigma.api.inventory.models.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class DirectionConverter implements Converter<String, Sort.Direction> {

    @Override
    public Sort.Direction convert(String source) {
        return Sort.Direction
                .fromOptionalString(source.toUpperCase())
                .orElseThrow();
    }

}
