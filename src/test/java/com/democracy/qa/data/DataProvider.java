package com.democracy.qa.data;

import com.democracy.qa.data.generator.DataFactory;
import com.democracy.qa.data.generator.TemplateParser;
import com.democracy.qa.exceptions.TestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataProvider {

    @Autowired
    private DataFactory factory;

    @Autowired
    private TemplateParser templateParser;

//    @Autowired
//    private JsonUtil jsonUtil;
//
//    public String populate(String template) throws TestException {
//        try {
//            String tmp = templateParser.parseTemplate(template, new HashMap<>(factory.getDataSet()));
//            return jsonUtil.stripTrailingJSONCommas(tmp);
//        } catch (Exception e) {
//            throw new TestException(e.getMessage());
//        }
//    }
//
//    public String populate(String template, Map<String, String> values) throws TestException {
//        Map<String, String> td = factory.getDataSet(values);
//        try {
//            String tmp = templateParser.parseTemplate(template, new HashMap<>(td));
//            return jsonUtil.stripTrailingJSONCommas(tmp);
//        } catch (Exception e) {
//            throw new TestException(e.getMessage());
//        }
//    }

    public String populateTemplateWithMapOfObject(String template, Map<String, Object> values) throws TestException {
        try {
            return templateParser.parseTemplate(template, values);
        } catch (Exception e) {
            throw new TestException(e.getMessage());
        }
    }

    public Map<String, String> updateTestDataSet(Map<String, String> from, Map<String, String> to) {
        return factory.updateDataSet(from, to);
    }

    public Map<String, String> parseDataSet(Map<String, String> values) {
        return factory.parseDataSet(values);
    }

    public Map<String, String> getDataSet() {
        return factory.parseDataSet(factory.getDataSet());
    }
}
