package org.example.model;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class Telescope extends Spacecraft{

    @Getter
    private Double distance;
    @Getter
    private Unit unit;
    @Getter
    private List<String> message;


    public Telescope(String name, double distance, Unit unit, List<String> message) {
        super(name);
        this.distance = distance;
        this.unit = checkUnit(unit);
        this.message = message;
    }

    private Unit checkUnit(Unit unit) {
        Optional<Unit> unitOptional = Optional.of(unit);
        if(unitOptional.isEmpty()) {
            return unit;
        }
        return Unit.KILOMETERS;
    }


}
