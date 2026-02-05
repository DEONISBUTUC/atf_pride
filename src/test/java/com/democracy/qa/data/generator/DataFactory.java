package com.democracy.qa.data.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataFactory {
    @Autowired
    private DataParser dataParser;

    @Autowired
    private FileReader fileReader;

    private Map<String, String> dataSet;

    @PostConstruct
    private void initDataSet() {
        dataSet = Collections.unmodifiableMap(fileReader.readData());
    }

    public Map<String, String> getDataSet() {
        return dataParser.parseTestData(dataSet);
    }

    public Map<String, String> getDataSet(Map<String, String> values) {
        Map<String, String> map = new HashMap<>(dataSet);
        return dataParser.parseTestData(updateDataSet(values, map));
    }

    public Map<String, String> updateDataSet(Map<String, String> fromSet, Map<String, String> destinationSet) {
        destinationSet.putAll(fromSet);
        return destinationSet;
    }

    public Map<String, String> parseDataSet(Map<String, String> values) {
        return dataParser.parseTestData(values);
    }

}
