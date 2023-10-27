package com.hexa.ums.converter;


import com.hexa.ums.exception.ConversionException;

import java.util.List;
import java.util.stream.Collectors;

public interface Populator<SOURCE, TARGET> {
    TARGET populate(SOURCE source) throws ConversionException;

    default List<TARGET> populateAll(List<SOURCE> sources) throws ConversionException {
        return sources.stream()
                .map(this::populate)
                .collect(Collectors.toList());
    }

    ;
}
