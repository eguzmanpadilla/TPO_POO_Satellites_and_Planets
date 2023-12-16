package org.example.view;

public record ResponseTopSecret(
        CoordinatesView position,
        String message,
        PlanetView exoplanet) {
}
