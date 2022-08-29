package com.timetable.program.controller;


import com.timetable.program.datamodel.TimeTable;
import com.timetable.program.dto.TimeTableDTO;
import com.timetable.program.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TimeTableController {

    @Autowired
    TimeTableService timeTableService;

    @GetMapping("/location/{id}")
    public TimeTableDTO showHour(@PathVariable String id) {

        return timeTableService.getTimeTable(id);
    }
}
