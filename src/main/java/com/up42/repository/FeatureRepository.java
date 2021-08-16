package com.up42.repository;

import com.up42.model.Feature;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeatureRepository {

    List<Feature> findAll();

    Optional<Feature> findById(UUID id);

    Feature save(Feature feature);

}
