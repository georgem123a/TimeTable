package com.timetable.program.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class TimeTableDTO {

    private String locationName;

    private String locationPlace;

    private List<Schedule> scheduleList;

    private Boolean closedOnHolidays;


}
