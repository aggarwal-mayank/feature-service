package com.up42.service;

import com.up42.exception.FeatureNotFoundException;
import com.up42.model.Feature;
import com.up42.repository.FeatureRepository;
import com.up42.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    private final ImageRepository imageRepository;

    public FeatureServiceImpl(FeatureRepository featureRepository, ImageRepository imageRepository) {
        this.featureRepository = featureRepository;
        this.imageRepository = imageRepository;
    }


    @Override
    public List<Feature> findAllFeatures() {
        return featureRepository
                .findAll();
    }

    @Override
    public Feature findFeatureById(String id) {
        return featureRepository
                .findById(UUID.fromString(id))
                .orElseThrow(FeatureNotFoundException::new);
    }

    @Override
    public byte[] findImageById(String id) {
        return Base64
                .getDecoder()
                .decode(imageRepository
                        .findById(id)
                        .orElseThrow(FeatureNotFoundException::new));
    }
}
