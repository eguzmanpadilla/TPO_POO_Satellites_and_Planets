package org.example.error;

public class SatelliteException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Theres not satellite defined.";

    public SatelliteException(String message) {
        super(message);
    }

    public SatelliteException() {
        super(DEFAULT_MESSAGE);
    }

}
