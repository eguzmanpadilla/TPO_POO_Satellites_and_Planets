package org.example.error;

public class MathException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Theres a math issue.";

    public MathException(String message) {
        super(message);
    }

    public MathException() {
        super(DEFAULT_MESSAGE);
    }
}
