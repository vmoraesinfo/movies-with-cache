package com.moraes.movies.DTO.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {
    private String lat;
    private String lon;
    @JsonProperty("display_name")
    private String displayName;
    private String name;
}
