package com.timetable.program.service;

import com.timetable.program.datamodel.TimeTable;
import com.timetable.program.dto.TimeTableDTO;

public interface TimeTableService {

    TimeTableDTO getTimeTable(String placeId);
}
