package com.timetable.program.datamodel;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DayName {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }


    @JsonCreator
    public  DayName fromString(String key) {
        return key == null
                ? null
                : DayName.valueOf(key.toUpperCase());
    }

    DayName(String value) {
        this.value = value;
    }
}
