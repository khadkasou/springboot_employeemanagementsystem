package com.emp.employee.service;
import com.emp.employee.dto.TimesheetsDto;
import com.emp.employee.model.Employee;
import com.emp.employee.model.Timesheets;
import com.emp.employee.repository.TimesheetRepo;
import com.emp.exceptions.GlobalExceptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TimesheetServiceImpl implements TimesheetsService{


    private TimesheetRepo timesheetRepo;
    private EmployeeService employeeService;

    @Override
    public Timesheets save(TimesheetsDto sheetsDto) {

        Timesheets sheets = new Timesheets();
        sheets.setUsername(sheetsDto.getUsername());
        sheets.setDate(sheetsDto.getDate());
        Employee employee= employeeService.findEmployeeByUsername(sheets.getUsername());

        if (employee==null){
            throw new GlobalExceptions("User with given username is available..");
        }
        if (sheets.getUsername()!=null && sheets.getDate()!=null){
            Timesheets existingTimesheet = timesheetRepo.findByUsernameAndDate(sheets.getUsername(), sheets.getDate());

            if (existingTimesheet!=null){
                throw new GlobalExceptions("You have already entry your timesheet...");
            }
            else {
                Timesheets lastestTimesheet = timesheetRepo.findByUsernameOrderByDate(
                        sheets.getUsername(),sheets.getDate());

                if (lastestTimesheet!=null && sheets.getDate().isBefore(lastestTimesheet.getDate())){
                   throw new GlobalExceptions("You cannot fill....");
                }

            }
        }
        sheets.setStartTime(sheetsDto.getStartTime());
        sheets.setEndTime(sheetsDto.getEndTime());
        return  timesheetRepo.save(sheets);
    }
    @Override
    public List<Timesheets> findByUsername(String username) {
        return timesheetRepo.findByUsername(username);
    }
    @Override
    public List<Timesheets> findByStartTimeAndEndTime(LocalTime startTime, LocalTime endTime) {
        return timesheetRepo.findByStartTimeAndEndTime(startTime, endTime);
    }
    @Override
    public List<Timesheets> findAll() {
       return timesheetRepo.findAll();
    }



}
