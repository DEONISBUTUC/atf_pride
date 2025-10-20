package org.example.context;

import java.util.HashMap;
import java.util.Map;

/**
 * ScenarioContext is a simple storage used to share data between Cucumber step definitions
 * during a single scenario execution.
 */
public class ScenarioContext {

    private static final ScenarioContext INSTANCE = new ScenarioContext();
    private final Map<String, Object> scenarioContext = new HashMap<>();

    public static ScenarioContext getInstance() {
        return INSTANCE;
    }

    public void setContext(String key, Object value) {
        if (scenarioContext.containsKey(key)) {
            throw new IllegalStateException("Key already exists: " + key);
        }
        scenarioContext.put(key, value);
    }

    public void scenarioUpdate(String key, Object value) {
        if (!scenarioContext.containsKey(key)) {
            throw new IllegalStateException("Key does not exist: " + key);
        }
        scenarioContext.put(key, value);
    }

    public Object getValue(String key) {
        if (!scenarioContext.containsKey(key)) {
            throw new IllegalStateException("Key does not exist: " + key);
        }
        return scenarioContext.get(key);
    }

    public <T> T getValueAs(String key, Class<T> tClass) {
        Object value = getValue(key);
        return tClass.cast(value);
    }

    public void clearScenarioContext() {
        scenarioContext.clear();
    }
}
