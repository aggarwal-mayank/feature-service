package com.up42.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private UUID id;

    private Long timestamp;

    private Acquisition acquisition;

}
