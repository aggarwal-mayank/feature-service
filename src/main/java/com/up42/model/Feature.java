package com.up42.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.up42.service.InstantSerializer;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class Feature {

    private UUID id;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant timestamp;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant beginViewingDate;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant endViewingDate;

    private String missionName;

    public Feature() {
    }

    public Feature(UUID id, Instant timestamp, Instant beginViewingDate, Instant endViewingDate, String missionName) {
        this.id = id;
        this.timestamp = timestamp;
        this.beginViewingDate = beginViewingDate;
        this.endViewingDate = endViewingDate;
        this.missionName = missionName;
    }
}
