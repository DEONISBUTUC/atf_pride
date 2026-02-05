package org.example.exceptions;

public class TestException extends RuntimeException {

    private static final String message = "Exception during test scenario execution";

    public TestException() {
        super(message);
    }

    public TestException(String msg) {
        super(msg);
    }
}