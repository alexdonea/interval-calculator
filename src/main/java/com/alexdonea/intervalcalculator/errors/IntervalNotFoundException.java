package com.alexdonea.intervalcalculator.errors;

public class IntervalNotFoundException extends RuntimeException {
    public IntervalNotFoundException(String message) {
        super(message);
    }

    public IntervalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntervalNotFoundException(Throwable cause) {
        super(cause);
    }
}
