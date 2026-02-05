package org.example.service;

import com.google.common.base.Joiner;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RestClient {

    @Autowired
    private TestRestTemplate restTemplate;

    @PostConstruct
    public void init(){
        RestTemplateBuilder builder = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(20));

        this.restTemplate = new TestRestTemplate(builder);
    }

    public ResponseEntity<?> get(String path) {
        return get(path, defaultHttpHeaders());
    }

    public ResponseEntity<?> get(String path, Map<String, String> params) {
        HttpEntity<?> httpEntity = new HttpEntity<>(defaultHttpHeaders());
        String parametrizedPath = addParamsToPath(path, params);
        return sendAndLogRequest(parametrizedPath, HttpMethod.GET, httpEntity);
    }

    public ResponseEntity<?> get(String path, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return sendAndLogRequest(path, HttpMethod.GET, httpEntity);
    }

    public ResponseEntity<?> get(String path, Map<String, String> params, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        String parametrizedPath = addParamsToPath(path, params);
        return sendAndLogRequest(parametrizedPath, HttpMethod.GET, httpEntity);
    }

    public ResponseEntity<?> post(String path, String body) {
        return post(path, body, defaultHttpHeaders());
    }

    public ResponseEntity<?> post(String path, Object body, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);
        return sendAndLogRequest(path, HttpMethod.POST, httpEntity);
    }

    ResponseEntity<?> post(String path, HttpEntity<?> httpEntity, HttpHeaders httpHeaders) {
        return sendAndLogRequest(path, HttpMethod.POST, httpEntity);
    }

    public ResponseEntity<?> put(String path, String body) {
        return put(path, body, defaultHttpHeaders());
    }

    public ResponseEntity<?> put(String path, String body, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);
        return sendAndLogRequest(path, HttpMethod.PUT, httpEntity);
    }

    public ResponseEntity<?> put(String path) {
        HttpEntity<?> httpEntity = new HttpEntity<>(defaultHttpHeaders());
        return sendAndLogRequest(path, HttpMethod.PUT, httpEntity);
    }

    public ResponseEntity<?> put(String path, Map<String, String> params) {
        return put(path, params, defaultHttpHeaders());
    }

    public ResponseEntity<?> put(String path, Map<String, String> params, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        String parametrizedPath = addParamsToPath(path, params);
        return sendAndLogRequest(parametrizedPath, HttpMethod.PUT, httpEntity);
    }

    public ResponseEntity<?> delete(String path) {
        return delete(path, defaultHttpHeaders());
    }

    public ResponseEntity<?> delete(String path, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return sendAndLogRequest(path, HttpMethod.DELETE, httpEntity);
    }

    public ResponseEntity<?> delete(String path, String body, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);
        return sendAndLogRequest(path, HttpMethod.DELETE, httpEntity);
    }

    public ResponseEntity<?> patch(String path, String body) {
        return patch(path, body, defaultHttpHeaders());
    }

    public ResponseEntity<?> patch(String path, Object body, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);
        return sendAndLogRequest(path, HttpMethod.PATCH, httpEntity);
    }

    ResponseEntity<?> patch(String path, HttpEntity<?> httpEntity, HttpHeaders httpHeaders) {
        return sendAndLogRequest(path, HttpMethod.PATCH, httpEntity);
    }

    public HttpHeaders defaultHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    public HttpHeaders defaultHttpHeaders(String authToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", authToken);
        return httpHeaders;
    }

    private ResponseEntity<?> sendAndLogRequest(String path, HttpMethod httpMethod, HttpEntity<?> httpEntity) {
        try {
            log.info("#");
            log.info("Sending: {} request on path: {} ", httpMethod, URI.create(path));
            log.debug("Sending: with headers: {}", httpEntity.getHeaders());
            log.debug("Sending: with body: {} ", httpEntity.getBody());

            ResponseEntity<?> responseEntity = restTemplate.exchange(URI.create(path), httpMethod, httpEntity, String.class);
            log.info("Received: status code: {}", responseEntity.getStatusCode().value());
            log.debug("Received: body {}", responseEntity.getBody());
            return responseEntity;
        } catch (HttpClientErrorException | HttpServerErrorException cx) {
            log.error("Got error: {}, {}, {}", cx.getStatusCode(), cx.getStatusText(), cx.getResponseBodyAsString());
            return new ResponseEntity<>(cx.getResponseBodyAsString(), cx.getStatusCode());
        }
    }

    private String addParamsToPath(String path, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        List<String> p = new ArrayList<>();
        params.forEach((k, v) -> p.add(String.format("%s=%s", k, v)));
        return sb.append(path).append("?").append(Joiner.on("&").join(p)).toString().replace(" ", "");
    }
}
