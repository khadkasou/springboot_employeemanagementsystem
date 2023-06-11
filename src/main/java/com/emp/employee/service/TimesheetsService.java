package com.emp.employee.service;
import com.emp.employee.dto.TimesheetsDto;
import com.emp.employee.model.Timesheets;
import java.time.LocalTime;
import java.util.List;

public interface TimesheetsService {
    public Timesheets save(TimesheetsDto timesheets);
//    public List<Timesheets> findByEmployeeId(String employeeId);
    public List<Timesheets> findByUsername(String username);

    public List<Timesheets>findByStartTimeAndEndTime(LocalTime startTime, LocalTime endTime);
    List<Timesheets> findAll();

}
