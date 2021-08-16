package com.up42.repository;

import com.up42.model.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class FeatureRepositoryImplTest {

    private final FeatureRepositoryImpl featureRepository = new FeatureRepositoryImpl();

    private Feature feature1;

    private Feature feature2;

    @BeforeEach
    public void setup() {
        feature1 = new Feature(UUID.randomUUID(), Instant.now(), Instant.now(), Instant.now(), "mission-1");
        feature2 = new Feature(UUID.randomUUID(), Instant.now(), Instant.now(), Instant.now(), "mission-2");
        featureRepository.save(feature1);
        featureRepository.save(feature2);
    }

    @Test
    void shouldReturnAllFeatures() {
        List<Feature> features = featureRepository.findAll();
        assertAll(
                () -> assertEquals(2, features.size())
        );
    }

    @Test
    void shouldReturnFeatureIfPresent() {
        Optional<Feature> featureOptional = featureRepository.findById(feature1.getId());
        assertAll(
                () -> assertTrue(featureOptional.isPresent()),
                () -> assertEquals(featureOptional.get(), feature1)
        );
    }

    @Test
    void shouldReturnEmptyOptionalIfFeatureNotPresent() {
        Optional<Feature> featureOptional = featureRepository.findById(UUID.randomUUID());
        assertAll(
                () -> assertTrue(featureOptional.isEmpty())
        );
    }

    @Test
    void shouldSaveAFeature() {
        Feature feature3 = new Feature(UUID.randomUUID(), Instant.now(), Instant.now(), Instant.now(), "mission-3");
        featureRepository.save(feature3);
        List<Feature> features = featureRepository.findAll();
        assertAll(
                () -> assertEquals(3, features.size())
        );
    }
}