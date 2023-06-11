package com.emp.employee.repository;

import com.emp.employee.dto.AttendanceDto;
import com.emp.employee.model.Attendance;
import com.emp.employee.model.AttendanceReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpAttendanceRepo extends MongoRepository<Attendance, String> {
    List<Attendance> findByUsernameAndDateBetween(String username,
                                                    LocalDate startDate, LocalDate endDate);
//    Attendance findByDateBetweenAndEmployeeId(String employeeId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Attendance> findByDateBetween(LocalDate startDate, LocalDate endDate);

//    List<AttendanceReport> saveAll(AttendanceReport attendanceReportList);

  public   Attendance save(AttendanceDto attendanceDto);
    Attendance findByUsernameAndDate(String username, LocalDate date);
    Attendance findByUsernameOrderByDateDesc(String username, LocalDate date);

    List<Attendance> findByUsername(String username);

    List<AttendanceReport> findAttendanceByUsername(String username);
}
