package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CelestialBody {
    private String name;
    private String typeOf;
    private double gravity;
    private double mass;

    public CelestialBody(String name,
                         double gravity,
                         double mass) {
        this.name = name;
        this.typeOf = getTypeOf();
        this.gravity = gravity;
        this.mass = mass;
    }

    public abstract String getTypeOf();



}
