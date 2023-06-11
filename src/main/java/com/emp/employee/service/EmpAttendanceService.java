package com.emp.employee.service;
import com.emp.employee.dto.AttendanceDto;
import com.emp.employee.model.Attendance;
import com.emp.employee.model.AttendanceReport;
import java.time.LocalDate;
import java.util.List;

public interface EmpAttendanceService {
    List<Attendance> generateAttendance(String username, LocalDate startDate, LocalDate endDate);

    List<AttendanceReport> generateAttendanceReport(LocalDate startDate, LocalDate endDate);

//    Attendance findByDateBetweenAndEmployeeId(String employeeId, LocalDateTime startDateTime, LocalDateTime endDateTime);

////    List<Attendance> saveAll();
//    List<AttendanceReport> saveAll(List<AttendanceReport> attendanceReportList);

   public Attendance save(AttendanceDto attendanceDto);
    public List<Attendance> findAll();

    public List<Attendance> absentEmployee(String id);

    List<Attendance> findByUsername(String username);

    List<AttendanceReport> findAttendanceByUsername(String username);
}
