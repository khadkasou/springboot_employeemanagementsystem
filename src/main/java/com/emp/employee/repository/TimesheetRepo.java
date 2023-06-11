package com.emp.employee.repository;

import com.emp.employee.dto.TimesheetsDto;
import com.emp.employee.model.Attendance;
import com.emp.employee.model.Timesheets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TimesheetRepo extends MongoRepository<Timesheets, String> {

    public List<Timesheets> findByUsername(String username);
    public List<Timesheets>findByStartTimeAndEndTime(LocalTime startTime, LocalTime endTime);

    public Timesheets save(TimesheetsDto timesheets);
    public Timesheets findByUsernameAndDate(String username, LocalDate date);
    Timesheets findByUsernameOrderByDate(String username, LocalDate date);


}
