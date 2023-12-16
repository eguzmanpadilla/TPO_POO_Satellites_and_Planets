package org.example.model;

import java.util.Optional;
import java.util.stream.Stream;

public enum Unit {

    METER,
    KILOMETERS,
    MEGAMETERS,
    GIGAMETERS;

    public static Unit findByName(Optional<String> name) {
        if(name.isEmpty()) {
            return Unit.KILOMETERS;
        }
        return Stream.of(Unit.values())
                .filter(category -> category.name().equalsIgnoreCase(name.get()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("System error"));
    }

}
