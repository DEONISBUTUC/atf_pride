package com.democracy.qa.data.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

@Slf4j
@Component
public class TemplateParser {

    @Value("${test.data.templates}")
    private String templateFolder;

    private Configuration cfg;

    public String parseTemplate(String templateName, Map<String, Object> templateParameters) throws Exception {
        Template template = cfg.getTemplate(templateName.replace(" ", "_") + ".ftl");
        Writer writer = new StringWriter();
        template.process(templateParameters, writer);
        return writer.toString();
    }

    @PostConstruct
    private void init() {
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_20);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(this.getClass(), templateFolder);
            cfg.setRecognizeStandardFileExtensions(true);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
