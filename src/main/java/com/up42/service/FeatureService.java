package com.up42.service;

import com.up42.model.Feature;

import java.util.List;

public interface FeatureService {

    List<Feature> findAllFeatures();

    Feature findFeatureById(String id);

    byte[] findImageById(String id);

}
