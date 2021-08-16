package com.up42.service;

import com.up42.exception.FeatureNotFoundException;
import com.up42.model.Feature;
import com.up42.repository.FeatureRepository;
import com.up42.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeatureServiceImplTest {

    @Mock
    private FeatureRepository featureRepository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private FeatureServiceImpl featureService;

    private List<Feature> features;

    private String imageString;

    private Feature feature1;

    private Feature feature2;

    @BeforeEach
    public void setup() throws IOException {
        feature1 = new Feature(UUID.randomUUID(), Instant.now(), Instant.now(), Instant.now(), "mission-1");
        feature2 = new Feature(UUID.randomUUID(), Instant.now(), Instant.now(), Instant.now(), "mission-2");
        features = new ArrayList<>();
        features.add(feature1);
        features.add(feature2);
        imageString = StreamUtils.copyToString(
                new ClassPathResource("image-string.txt").getInputStream(), defaultCharset());
    }

    @Test
    void shouldGetAllFeatures() {
        when(featureRepository.findAll()).thenReturn(features);
        List<Feature> allFeatures = featureService.findAllFeatures();
        assertAll(
                () -> assertEquals(2, allFeatures.size())
        );

    }

    @Test
    void shouldGetFeatureByIdIfPresent() {
        when(featureRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(feature1));
        Feature feature = featureService.findFeatureById("39c2f29e-c0f8-4a39-a98b-deed547d6aea");
        assertAll(
                () -> assertEquals(feature1, feature)
        );
    }

    @Test
    void shouldThrowExceptionIfFeatureNotPresent() {
        when(featureRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(FeatureNotFoundException.class,
                () -> featureService.findFeatureById("39c2f29e-c0f8-4a39-a98b-deed547d6aea"));
    }

    @Test
    void shouldGetImageIfFeatureIsPresent() {
        when(imageRepository.findById(any(String.class))).thenReturn(Optional.of(imageString));
        byte[] image = featureService.findImageById("39c2f29e-c0f8-4a39-a98b-deed547d6aea");
        assertAll(
                () -> assertNotEquals(0, image.length)
        );
    }

    @Test
    void shouldThrowExceptionForImageIfFeatureNotPresent() {
        when(imageRepository.findById(any(String.class))).thenReturn(Optional.empty());
        assertThrows(FeatureNotFoundException.class,
                () -> featureService.findImageById("39c2f29e-c0f8-4a39-a98b-deed547d6aea"));
    }
}
