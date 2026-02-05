package org.example.exceptions;

public class TestResourceNotFoundException extends Exception {

    private static final String message = "Resource you are trying to access in ScenarioContext/GlobalContext container has not been found";

    public TestResourceNotFoundException() {
        super(message);
    }
}
