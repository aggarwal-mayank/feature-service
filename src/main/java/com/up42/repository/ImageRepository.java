package com.up42.repository;

import com.jayway.jsonpath.DocumentContext;

import java.util.Optional;

public interface ImageRepository {

    Optional<String> findById(String id);

    void saveContext(DocumentContext context);

}
