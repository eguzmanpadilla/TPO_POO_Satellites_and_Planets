package org.example.model;

import lombok.Getter;
import lombok.Setter;


public class Exoplanet extends CelestialBody {

    private static final String TYPE_OF = "Planet";
    @Setter
    @Getter
    private short moonAmount;
    @Setter
    @Getter
    private boolean radioactive;

    public Exoplanet(String name,
                     Double gravity,
                     Double mass,
                     Short moonAmount,
                     Boolean radioactive) {
        super(name, gravity, mass);
        this.moonAmount = moonAmount;
        this.radioactive = radioactive;

    }

    @Override
    public String getTypeOf() {
        return TYPE_OF;
    }


}
