package org.example.view;

public record PlanetView(String name,
                         Double gravity,
                         Double mass,
                         Short moonAmount,
                         Boolean radioactive) {
}
