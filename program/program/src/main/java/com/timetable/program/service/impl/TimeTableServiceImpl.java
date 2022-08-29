package com.timetable.program.service.impl;

import com.timetable.program.datamodel.DayInfo;
import com.timetable.program.datamodel.DayName;
import com.timetable.program.datamodel.TimeTable;
import com.timetable.program.dto.Schedule;
import com.timetable.program.dto.TimeTableDTO;
import com.timetable.program.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public TimeTableDTO getTimeTable(String placeId) {

        String url = "https://storage.googleapis.com/coding-session-rest-api/" + placeId;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        TimeTable timeTable = restTemplate.exchange(url, HttpMethod.GET, entity, TimeTable.class).getBody();

        TimeTableDTO timeTableDTO = new TimeTableDTO();
        timeTableDTO.setLocationPlace(timeTable.getLocationPlace());
        timeTableDTO.setLocationName(timeTable.getLocationName());
        timeTableDTO.setScheduleList(getScheduleFromTimeTable(timeTable));
        Boolean closedOnHoliday = (Boolean) timeTable.getOpeningHours().get("closed_on_holidays");
        closedOnHoliday = closedOnHoliday != null ? closedOnHoliday : false;
        timeTableDTO.setClosedOnHolidays(closedOnHoliday);


        return timeTableDTO;
    }

    private List<Schedule> getScheduleFromTimeTable(TimeTable timeTable) {

        List<Schedule> scheduleList = new ArrayList<>();
        LinkedHashMap<String, List<DayInfo>> dayInfoTime = (LinkedHashMap<String, List<DayInfo>>) timeTable.getOpeningHours().get("days");

        System.out.println(dayInfoTime);
        dayInfoTime.entrySet().forEach(dayNameListEntry -> {
            Boolean matchingExistingProgram = false;
            for (Schedule schedule : scheduleList) {
                if ((!schedule.getDayNameGroup().contains(dayNameListEntry.getKey())) && dayNameListEntry.getValue().equals(schedule.getDayInfoList())) {
                    schedule.setDayNameGroup(schedule.getDayNameGroup() + "-" + dayNameListEntry.getKey());
                    matchingExistingProgram = true;
                }
            }
            if (!matchingExistingProgram) {
                scheduleList.add(new Schedule(dayNameListEntry.getKey(), dayNameListEntry.getValue()));
            }
        });
        return scheduleList;
    }


}
