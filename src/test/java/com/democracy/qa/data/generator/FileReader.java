package com.democracy.qa.data.generator;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class FileReader {

    @Value("${test.data.file}")
    private String testDataFile;


    Map<String, String> readData() throws RuntimeException {
        return readDataFromFile(testDataFile);
    }

    public Map<String, String> readDataFromFile(String filePath) {
        HashMap<String, String> resultMap = new HashMap<>();
        try (InputStreamReader inputStream = getInputStream(filePath)) {
            List<String[]> linesList = new CSVReader(inputStream).readAll();
            for (String[] row : linesList) {
                resultMap.put(trim(row[0]), trim(row[1]));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
        return resultMap;
    }

    private String trim(String s) {
        String[] whitespaceCharsArray = {"\b", "\n", "\r", "\t"};
        for (String ws : whitespaceCharsArray) {
            s.replaceAll(ws, "").trim();
        }
        return s.strip().replace("\"\"", "\"");
    }

    private InputStreamReader getInputStream(String resource) {
        return new InputStreamReader(FileReader.class.getResourceAsStream(resource));
    }
}
