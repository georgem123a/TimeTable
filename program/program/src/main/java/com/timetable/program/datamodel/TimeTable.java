package com.timetable.program.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class TimeTable {

    @JsonProperty("displayed_what")
    private String locationName;

    @JsonProperty("displayed_where")
    private String locationPlace;

    @JsonProperty("opening_hours")
    private LinkedHashMap<String,Object> openingHours;
}
