package com.up42.repository;

import com.up42.model.Feature;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FeatureRepositoryImpl implements FeatureRepository {

    private final Map<UUID, Feature> datastore = new HashMap<>();

    @Override
    public List<Feature> findAll() {
        return new ArrayList<>(datastore.values());
    }

    @Override
    public Optional<Feature> findById(UUID id) {
        return Optional.ofNullable(datastore.get(id));
    }

    @Override
    public Feature save(Feature feature) {
        return datastore.put(feature.getId(), feature);
    }
}
