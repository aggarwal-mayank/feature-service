package com.up42.repository;

import com.jayway.jsonpath.DocumentContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    private DocumentContext context;

    @Override
    public Optional<String> findById(String id) {
        List<String> stringList = context.read("$..[?(@.id=='" + id + "')].quicklook");
        return stringList.isEmpty() ? Optional.empty() : Optional.of(stringList.get(0));
    }

    @Override
    public void saveContext(DocumentContext context) {
        this.context = context;
    }
}
