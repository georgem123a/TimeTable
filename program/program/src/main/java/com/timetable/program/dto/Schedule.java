package com.timetable.program.dto;

import com.timetable.program.datamodel.DayInfo;
import com.timetable.program.datamodel.DayName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
public class Schedule {

    private String dayNameGroup;
    private List<DayInfo> dayInfoList;


}
