package com.up42.repository;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.Optional;

import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class ImageRepositoryImplTest {

    private ImageRepositoryImpl imageRepository = new ImageRepositoryImpl();

    @BeforeEach
    public void setup() throws IOException {
        DocumentContext context = JsonPath
                .using(Configuration.defaultConfiguration())
                .parse(StreamUtils.copyToString(
                        new ClassPathResource("source-data-2.json").getInputStream(), defaultCharset()));
        imageRepository.saveContext(context);
    }

    @Test
    void shouldReturnImageStringIfPresent() {
        Optional<String> imageOptional = imageRepository.findById("39c2f29e-c0f8-4a39-a98b-deed547d6aea");
        assertAll(
                () -> assertTrue(imageOptional.isPresent()),
                () -> assertEquals("iVBORw0", imageOptional.get())
        );
    }

    @Test
    void shouldReturnEmptyOptionalIfFeatureNotPresent() {
        Optional<String> imageOptional = imageRepository.findById("39c2f29e-c0f8-4a39-a98b-deed547d6aeb");
        assertAll(
                () -> assertTrue(imageOptional.isEmpty())
        );
    }

}