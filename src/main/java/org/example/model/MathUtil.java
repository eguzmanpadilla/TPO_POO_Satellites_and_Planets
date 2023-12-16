package org.example.model;

import org.example.error.MathException;

public class MathUtil {


    public Coordinate trilateration(double x1, double y1, double r1,
                                    double x2, double y2, double r2,
                                    double x3, double y3, double r3) {

        double distance = getDistance(x1, y1, x2, y2);
        if(!(r1 <= distance && r2 <= distance && r1+r2 >= distance)) {
            throw new MathException("The circumferences dont touch each-other.");
        }

        double projection = getProjection(r1, r2, distance);
        double heightToPoint = pitagoras(r1, projection);

        double xMiddle = x1 + projection * (x2 - x1) / heightToPoint;
        double yMiddle = y1 + projection * (y2 - y1) / heightToPoint;

        double xResA = xMiddle + heightToPoint * (y2 - y1) / distance;
        double yResA = yMiddle - heightToPoint * (x2 - x1) / distance;
        double distanceA = getDistance(xResA, yResA, x3, y3);

        double xResB = xMiddle - heightToPoint * (y2 - y1) / distance;
        double yResB = yMiddle + heightToPoint * (x2 - x1) / distance;
        double distanceB = getDistance(xResB, yResB, x3, y3);

        if(Double.compare(distanceA, r3) == 0){
            return new Coordinate(xResA, yResA);
        } else if (Double.compare(distanceB, r3) == 0) {
            return new Coordinate(xResB, yResB);
        } else {
            throw new MathException("The circumferences dont touch each-other.");
        }

    }

    public double getDistance(double x1, double y1, double x2, double y2) {

        double xDiff = 0;
        if(x1 < 0 && x2 >= 0) {
            xDiff = x2 - x1;
        } else if (x2 < 0 && x1 >= 0) {
            xDiff = x1 - x2;
        } else {
            xDiff = Math.abs(x1 + x2);
        }

        double yDiff = 0;
        if(y1 < 0 && y2 >= 0) {
            xDiff = y2 - y1;
        } else if (y2 < 0 && y1 >= 0) {
            xDiff = y1 - y2;
        } else {
            xDiff = Math.abs(y1 + y2);
        }

        return pitagoras(xDiff, yDiff);

    }

    public double pitagoras(double xLarge, double yLarge) {
        return Math.sqrt(Math.pow(xLarge,2) + Math.pow(yLarge,2));
    }

    public double getProjection(double r1, double r2, double d ) {
        return (Math.pow(r1, 2) - Math.pow(r2, 2) + Math.pow(d, 2)) / (2 * d);
    }




}
