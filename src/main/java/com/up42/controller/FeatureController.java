package com.up42.controller;

import com.up42.exception.FeatureNotFoundException;
import com.up42.model.Feature;
import com.up42.service.FeatureService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/features")
public class FeatureController {

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Feature> getAllFeatures() {
        return featureService.findAllFeatures();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Feature getFeatureById(@PathVariable String id) throws FeatureNotFoundException {
        return featureService.findFeatureById(id);
    }

    @GetMapping(value = "/{id}/quicklook", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImageById(@PathVariable String id) throws FeatureNotFoundException {
        return featureService.findImageById(id);
    }
}
