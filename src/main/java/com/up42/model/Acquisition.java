package com.up42.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Acquisition {

    private Long beginViewingDate;

    private Long endViewingDate;

    private String missionName;

}
