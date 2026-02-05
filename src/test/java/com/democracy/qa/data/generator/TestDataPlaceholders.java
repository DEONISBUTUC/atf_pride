package com.democracy.qa.data.generator;

public enum TestDataPlaceholders {
    EMPTY_SPACE("empty", ""), BLANK("blank", " ");

    String placeholderName;
    String placeholderValue;

    TestDataPlaceholders(String name, String value) {
        placeholderName = name;
        placeholderValue = value;
    }

    String getPlaceholderName() {
        return placeholderName;
    }

    String getPlaceholderValue() {
        return placeholderValue;
    }
}

