package org.example.model;

import org.example.error.MathException;
import org.example.error.SatelliteException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SatelliteService {


    private List<Satellite> satellites;
    private List<Telescope> telescopes;
    private MathUtil mathUtil;
    private List<Exoplanet> planets;



    @PostConstruct
    public void postConstruct() {
        this.satellites = new ArrayList<>(3);
        this.telescopes = new ArrayList<>(3);
        this.mathUtil = new MathUtil();
        this.planets = List.of(
                new Exoplanet(
                        "Jupiter",
                        24.79D,
                        1.898D,
                        (short) 79,
                        false ),
                new Exoplanet(
                        "Venus",
                        8.87D,
                        4.867D,
                        (short) 0,
                        false),
                new Exoplanet(
                        "Saturno",
                        10.44D,
                        5.6834D,
                        (short) 82,
                        false)

                );
    }


    public void defInfo(List<Satellite> satelliteList, List<Telescope> telescopeList ) {
        clearInfo();
        if(!satellites.isEmpty() || !telescopes.isEmpty()) {
            throw new SatelliteException("data is already defined");
        }
        this.satellites.addAll(satelliteList);
        this.telescopes.addAll(telescopeList);

    }

    public Exoplanet getPlanetData() {
        return getRandomElement(this.planets);
    }

    private static<T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public void clearInfo() {
        this.satellites.clear();
        this.telescopes.clear();
    }

    public Coordinate getExoplanetCoords() {
        if(this.satellites.isEmpty() || this.telescopes.isEmpty()) {
            throw new SatelliteException();
        }

        Coordinate coordsA = this.satellites.get(0).getCoords();
        Coordinate coordsC = this.satellites.get(1).getCoords();
        Coordinate coordsB = this.satellites.get(2).getCoords();

        if(coordsA.equals(coordsB) || coordsA.equals(coordsC) || coordsB.equals(coordsC)) {
            throw new SatelliteException("Satellite coords remains same place for distinct satellites.");
        }

        Telescope telescope1 = this.telescopes.get(0);
        Telescope telescope2 = this.telescopes.get(1);
        Telescope telescope3 = this.telescopes.get(2);


        double distanceA = telescope1.getDistance();
        Unit unitA = telescope1.getUnit();
        distanceA = getToKilometers(distanceA, unitA);

        double distanceB = telescope2.getDistance();
        Unit unitB = telescope2.getUnit();
        distanceB = getToKilometers(distanceB, unitB);

        double distanceC = telescope3.getDistance();
        Unit unitC = telescope3.getUnit();
        distanceC = getToKilometers(distanceC, unitC);

        if(Double.compare(distanceA,0D) <= 0 || Double.compare(distanceB, 0D) <= 0 || Double.compare(distanceC,0D) <= 0) {
            throw  new SatelliteException("Satellite does not calculate distance");
        }

        try {
            return this.mathUtil.trilateration(
                    coordsA.x(), coordsA.y(), distanceA,
                    coordsB.x(), coordsB.y(), distanceB,
                    coordsC.x(), coordsC.y(), distanceC);

        } catch(MathException ex) {
            return new Coordinate(null,null);
        }

    }

    public List<String> getMessageList() {
        if(this.satellites.isEmpty()) {
            throw new SatelliteException("You need to define the satellite information first.");
        }
        List<String> msg1 = this.telescopes.get(0).getMessage();
        List<String> msg2 = this.telescopes.get(1).getMessage();
        List<String> msg3 = this.telescopes.get(2).getMessage();
        int max = Math.max(msg3.size(), Math.max(msg1.size(), msg2.size()));
        List<String> finalText;
        if(max == msg1.size()) {
            finalText = msg1;
            msg1 = (msg1.stream().filter(s -> finalText.indexOf(s) != 0)).toList();
        } else if(max == msg2.size()) {
            finalText = msg2;
            msg2 = (msg2.stream().filter(s -> finalText.indexOf(s) != 0)).toList();
        } else if (max == msg3.size()) {
            finalText = msg3;
            msg3 = (msg3.stream().filter(s -> finalText.indexOf(s) != 0)).toList();
        }

        List<String> result = new ArrayList<>();
        for(int i = 0 ; i < msg1.size() ; i++) {
            if( !msg1.get(i).isEmpty()) {
                String word = checkLetters(msg1.get(i));
                result.add(word);
            }else if( !msg2.get(i).isEmpty()) {
                String word = checkLetters(msg2.get(i));
                result.add(word);
            }else if ( !msg3.get(i).isEmpty()) {
                String word = checkLetters(msg3.get(i));
                result.add(word);
            }
        }
        return result;
    }

    public String getMessage() {
        List<String> msg = getMessageList();
        StringBuilder template = new StringBuilder();
        for (String word : msg) {
            template.append(word).append(" ");
        }
        return template.toString();
    }

    private String checkLetters(String word) {
        StringBuilder template = new StringBuilder();
        for(int i = 0 ; i < word.length() ; i++) {
            if(Character.isLetter(word.charAt(i))) {
                template.append(word.charAt(i));
            }
        }
        return template.toString();
    }

    private double getToKilometers(double distance, Unit unit) {
        if(unit.equals(Unit.METER)) {
            distance = distance / 1000;
        } else if(unit.equals(Unit.MEGAMETERS)) {
            distance = distance * 1000;
        }
        else if(unit.equals(Unit.GIGAMETERS)) {
            distance = distance * Math.pow(10,6);
        }
        return distance;
    }

}
