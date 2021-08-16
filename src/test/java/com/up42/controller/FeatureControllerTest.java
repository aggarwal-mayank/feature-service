package com.up42.controller;

import com.up42.model.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeatureControllerTest {

    @LocalServerPort
    private Integer port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(HttpStatus httpStatus) {
                return false;
            }
        });
    }

    @BeforeEach
    void setup() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/features");
    }

    @Test
    void shouldGetAllFeatures() {
        ResponseEntity<Feature[]> responseEntity =
                restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<String>(""), Feature[].class);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(14, responseEntity.getBody().length),
                () -> assertEquals("application/json", responseEntity.getHeaders().getContentType().toString())
        );
    }

    @Test
    void shouldGetFeatureById() {
        ResponseEntity<Feature> responseEntity =
                restTemplate.exchange(baseUrl.concat("/39c2f29e-c0f8-4a39-a98b-deed547d6aea"), HttpMethod.GET, new HttpEntity<String>(""), Feature.class);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals("39c2f29e-c0f8-4a39-a98b-deed547d6aea", responseEntity.getBody().getId().toString()),
                () -> assertEquals(1554831167697L, responseEntity.getBody().getTimestamp().toEpochMilli()),
                () -> assertEquals(1554831167697L, responseEntity.getBody().getBeginViewingDate().toEpochMilli()),
                () -> assertEquals(1554831202043L, responseEntity.getBody().getEndViewingDate().toEpochMilli()),
                () -> assertEquals("Sentinel-1B", responseEntity.getBody().getMissionName())
        );
    }

    @Test
    void shouldReturnNotFoundWhenFeatureIsNotFound() {
        HttpEntity<String> request = new HttpEntity<>("", new HttpHeaders());
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(baseUrl.concat("/39c2f29e-c0f8-4a39-a98b-deed547d6aeb"), HttpMethod.GET, request, String.class);
        assertAll(
                () -> assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode()),
                () -> assertNull(responseEntity.getBody())
        );
    }

    @Test
    void shouldGetImageById() {
        ResponseEntity<byte[]> responseEntity =
                restTemplate.exchange(baseUrl.concat("/39c2f29e-c0f8-4a39-a98b-deed547d6aea/quicklook"), HttpMethod.GET, new HttpEntity<String>(""), byte[].class);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(236829, responseEntity.getBody().length),
                () -> assertEquals("image/png", responseEntity.getHeaders().getContentType().toString())
        );
    }

    @Test
    void shouldReturnNotFoundForImageWhenFeatureIsNotFound() {
        HttpEntity<String> request = new HttpEntity<>("", new HttpHeaders());
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(baseUrl.concat("/39c2f29e-c0f8-4a39-a98b-deed547d6aeb/quicklook"), HttpMethod.GET, request, String.class);
        assertAll(
                () -> assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode()),
                () -> assertNull(responseEntity.getBody())
        );
    }
}