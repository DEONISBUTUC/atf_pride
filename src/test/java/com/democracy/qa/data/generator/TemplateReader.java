package com.democracy.qa.data.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class TemplateReader {

    private final ResourceLoader resourceLoader;

    public String readTemplate(String templatePath) {

        try {
            Resource resource = resourceLoader.getResource("classpath:" + templatePath);

            return new String(
                    resource.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Cannot read template: " + templatePath, e
            );
        }
    }
}