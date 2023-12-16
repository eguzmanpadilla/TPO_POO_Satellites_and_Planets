package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.*;
import org.example.view.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/process")
public class ProcessController {

    protected SatelliteService satelliteService;

    @PostMapping("/topsecret")
    public ResponseEntity<?> defInfo(
            @RequestBody RequestTopSecret requestTopSecret) {
        this.satelliteService.defInfo(mapSat(requestTopSecret.satellites()),mapTel(requestTopSecret.telescopes()));

        ResponseTopSecret result = new ResponseTopSecret(

                mapCoordsShow(this.satelliteService.getExoplanetCoords()),

                this.satelliteService.getMessage(),

                mapPlanetView(this.satelliteService.getPlanetData()));

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/topsecret/cleardata")
    public ResponseEntity<?> clear() {
        this.satelliteService.clearInfo();
        return ResponseEntity.ok().build();
    }

    private List<Satellite> mapSat(List<SatelliteView> satelliteViews) {
        List<Satellite> mapped = new ArrayList<>();
        for(SatelliteView current : satelliteViews) {
            mapped.add(new Satellite(current.name(), mapCoords(current.coordinates()), current.workers()));
        }
        return mapped;
    }

    private Coordinate mapCoords(CoordinatesView coordinates) {
        return new Coordinate(coordinates.x(), coordinates.y());
    }

    private CoordinatesView mapCoordsShow(Coordinate coordinate) {
        return new CoordinatesView(coordinate.x(), coordinate.y());
    }

    private List<Telescope> mapTel(List<TelescopeView> telescopes) {
        List<Telescope> mapped = new ArrayList<>();
        for(TelescopeView current : telescopes) {
            mapped.add(new Telescope(
                    current.name(),
                    current.distance(),
                    Unit.findByName(Optional.ofNullable(current.unit())),
                    current.message() ));
        }
        return mapped;
    }

    private PlanetView mapPlanetView(Exoplanet planet) {
        return new PlanetView(
                planet.getName(),
                planet.getGravity(),
                planet.getMass(),
                planet.getMoonAmount(),
                planet.isRadioactive());
    }

}