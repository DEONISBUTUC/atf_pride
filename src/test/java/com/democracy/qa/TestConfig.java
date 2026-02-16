package com.democracy.qa;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:application-${env:local}.properties")
public class TestConfig {

    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate(
                TestRestTemplate.HttpClientOption.ENABLE_REDIRECTS,
                TestRestTemplate.HttpClientOption.ENABLE_COOKIES,
                TestRestTemplate.HttpClientOption.SSL);
    }
}