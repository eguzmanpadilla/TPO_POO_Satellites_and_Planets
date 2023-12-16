package org.example.model;

import lombok.Getter;


@Getter
public class Satellite extends Spacecraft {

    private Coordinate coords;
    private int workers;

    public Satellite(String name, Coordinate coords, int workers) {
        super(name);
        this.coords = coords;
        this.workers = workers;
    }


}
