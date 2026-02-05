package com.democracy.qa.util;

import com.democracy.qa.data.DataProvider;
import com.democracy.qa.exceptions.TestException;
import com.democracy.qa.exceptions.TestResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;


@Component
public class ScenarioContext {

    @Autowired
    DataProvider dataProvider;

    private HashMap<SCKey, Object> scenarioResources = new HashMap<>();

    private LinkedList<ResponseEntity<?>> responseList = new LinkedList<>();

    private HashMap<String, String> scenarioDataSet;

    @PostConstruct
    public void init() {
        this.scenarioDataSet = new HashMap<>();
    }

    public <T> T getScenarioResource(SCKey key) throws TestResourceNotFoundException {
        if (!scenarioResources.containsKey(key)) {
            throw new TestResourceNotFoundException();
        }
        return (T) scenarioResources.get(key);
    }

    public void putScenarioResource(SCKey key, Object obj) throws TestException {
        if (scenarioResources.containsKey(key)) {
            throw new TestException("Resource already exists. If you try to update use: updateScenarioResource method");
        } else {
            scenarioResources.put(key, obj);
        }
    }

    public void updateScenarioResource(SCKey key, Object obj) throws TestException {
        if (scenarioResources.containsKey(key)) {
            scenarioResources.replace(key, obj);
        } else {
            throw new TestException("Resource already exists. If you try to update use: putScenarioResource method");
        }
    }

    public boolean scenarioResourceExists(SCKey key) {
        return scenarioResources.containsKey(key);
    }

    public void addResponse(ResponseEntity<?> response) {
        responseList.add(response);
    }

    public ResponseEntity<?> getLastResponse() {
        return responseList.getLast();
    }

    public List<ResponseEntity<?>> getResponseList() {
        return Collections.unmodifiableList(new LinkedList<>(responseList));
    }

    public HashMap<String, String> getScenarioDataSet() {
        if (scenarioDataSet.isEmpty()) {
            updateScenarioDataSet(dataProvider.getDataSet());
        }
        return scenarioDataSet;
    }

    public HashMap<String, String> updateScenarioDataSet(Map<String, String> withData) {
        if (scenarioDataSet.isEmpty()) {
            scenarioDataSet.putAll(dataProvider.getDataSet());
        }
        scenarioDataSet.putAll(withData);
        return scenarioDataSet;
    }

    public HashMap<String, String> updateScenarioDataSet(String key, String value) {
        if (scenarioDataSet.isEmpty()) {
            updateScenarioDataSet(dataProvider.getDataSet());
        }
        scenarioDataSet.put(key, value);
        return scenarioDataSet;
    }

    public void clearScenarioContext() {
        scenarioDataSet.clear();
        responseList.clear();
        scenarioResources.clear();
    }
}
