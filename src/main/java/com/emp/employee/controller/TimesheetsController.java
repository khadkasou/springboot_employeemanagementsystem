package com.emp.employee.controller;

import com.emp.employee.dto.TimesheetsDto;
import com.emp.employee.model.Timesheets;
import com.emp.employee.service.TimesheetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/emp")
public class TimesheetsController {

    @Autowired
    private TimesheetsService timesheetsService;

//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER') or hasAuthority('EMPLOYEE')")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("/save/timesheets")
    public ResponseEntity<?> saveTimesheet(@RequestBody TimesheetsDto timesheets){

        Timesheets sheets=  timesheetsService.save(timesheets);
        return new ResponseEntity<>(sheets, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')" )
    @GetMapping("{/employeeId}")
    public ResponseEntity<List<Timesheets>> getAllTimesheets(@RequestParam("username") String username){

        List<Timesheets> timeSheets= timesheetsService.findByUsername(username);
        return new ResponseEntity<>(timeSheets, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Timesheets>> getTimeSheetsByDate(@RequestParam("startDate")
                                                                @DateTimeFormat(pattern ="hh:mm") LocalTime startTime,
                                                                @RequestParam("endDate")
                                                                @DateTimeFormat(pattern ="hh:mm") LocalTime endTime){

      List<Timesheets> timesheets= timesheetsService.findByStartTimeAndEndTime(startTime,endTime);
        return new ResponseEntity<>(timesheets, HttpStatus.OK);

    }
}
