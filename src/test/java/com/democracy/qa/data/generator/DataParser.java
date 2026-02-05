package com.democracy.qa.data.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class DataParser {
    @Value("${test.data.separator}")
    private String separator;

    @Autowired
    private DataGenerator generator;

    public Map<String, String> parseTestData(Map<String, String> rawTestDataEntries) {
        HashMap<String, String> testDataMap = removeSkippedEntries(new HashMap<>(rawTestDataEntries));
        try {
            testDataMap.forEach((k, v) -> {
                testDataMap.replace(k, (compute(v)));
            });

            testDataMap.forEach((k, v) -> {
                if (isPlaceholderEntry(v)) {
                    testDataMap.replace(k, getPlaceholderValue(v));
                }
            });

            testDataMap.forEach((k, v) -> {
                if (v.contains("copy")) {
                    testDataMap.replace(k, testDataMap.get(v.split(separator)[1]));
                }
            });

            testDataMap.forEach((k, v) -> {
                if (v.contains("concat")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String[] concatKeys = v.split(separator)[1].split("\\+");
                    for (String s : concatKeys) {
                        stringBuilder.append(testDataMap.getOrDefault(s, s));
                    }
                    testDataMap.replace(k, stringBuilder.toString());
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return testDataMap;
    }

    private String compute(String tdEntry) {
        String methodName = getMethodName(tdEntry);
        if (methodName != null) {
            try {
                String[] args = getArgs(tdEntry);
                if (args.length == 0) {
                    Method method = generator.getClass().getDeclaredMethod(methodName);
                    return (String) method.invoke(generator);
                } else {
                    Class[] argTypes = new Class[args.length];
                    for (int i = 0; i < args.length; i++) {
                        argTypes[i] = String.class;
                    }
                    Method method = generator.getClass().getDeclaredMethod(methodName, argTypes);
                    return (String) method.invoke(generator, args);
                }
            } catch (NoSuchMethodException ex) {
                return tdEntry;
            } catch (Exception ex) {
                log.error(ex.getMessage() + " for method call: " + methodName);
            }
        }
        return tdEntry;
    }

    private String getMethodName(String tdEntry) {
        Matcher m = Pattern.compile("(.*?)\\(").matcher(tdEntry);
        return m.find() ? m.group(1) : null;
    }

    private String[] getArgs(String tdEntry) {
        String args = tdEntry.substring(tdEntry.indexOf("(") + 1, tdEntry.indexOf(")"));
        if (args.isEmpty()) {
            return new String[]{};
        }
        return args.contains(separator) ? args.split(separator) : new String[]{args};
    }

    private String getPlaceholderValue(String placeholderName) {
        for (TestDataPlaceholders t : TestDataPlaceholders.values()) {
            if (placeholderName.equalsIgnoreCase(t.getPlaceholderName())) {
                return t.getPlaceholderValue();
            }
        }
        return placeholderName;
    }

    private HashMap<String, String> removeSkippedEntries(HashMap<String, String> testDataMap) {
        ArrayList<String> skippedList = new ArrayList<>();
        testDataMap.forEach((k, v) -> {
            if (v.equalsIgnoreCase("skip") || v.equalsIgnoreCase("missing")) {
                skippedList.add(k);
            }
        });
        for (String s : skippedList) {
            testDataMap.remove(s);
        }
        return testDataMap;
    }

    private boolean isPlaceholderEntry(String testDataVariable) {
        for (TestDataPlaceholders t : TestDataPlaceholders.values()) {
            if (testDataVariable.toLowerCase().contains(t.getPlaceholderName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
