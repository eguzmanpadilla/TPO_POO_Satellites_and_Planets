package org.example.view;

public record SatelliteView(
        String name,
        CoordinatesView coordinates,
        Integer workers) {
}
