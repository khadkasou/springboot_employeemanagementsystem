package com.emp.employee.service;
import com.emp.employee.dto.AttendanceDto;
import com.emp.employee.model.Attendance;
import com.emp.employee.model.AttendanceReport;
import com.emp.employee.model.AttendanceStatus;
import com.emp.employee.model.Employee;
import com.emp.employee.repository.EmpAttendanceRepo;
import com.emp.employee.repository.EmployeeRepo;
import com.emp.exceptions.GlobalExceptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class EmpAttendanceServiceImpl implements EmpAttendanceService{

    private EmpAttendanceRepo empAttendanceRepo;

    private EmployeeRepo employeeRepo;

    @Override
    public List<Attendance> generateAttendance(String username,
                                               LocalDate startDate, LocalDate endDate) {

        return empAttendanceRepo.findByUsernameAndDateBetween(username,startDate,endDate);

    }



    @Override
    public List<AttendanceReport> generateAttendanceReport(LocalDate startDate, LocalDate endDate) {

        // Get attendance records from MongoDB based on date range
        List<Attendance> attendanceList= empAttendanceRepo.findByDateBetween(startDate, endDate);

        // Create a map to store attendance data
        Map<String,AttendanceReport> attendanceReportMap=new HashMap<>();

        // Iterate through the attendance records and populate the attendance report map
        for (Attendance attendance: attendanceList){
            String username=attendance.getUsername();

            if (!attendanceReportMap.containsKey(username)){
                AttendanceReport attendanceReport=new AttendanceReport();

//                attendanceReport.setEmployeeId(employeeId);
                attendanceReport.setTotalDaysPresent(0);
                attendanceReport.setTotalDaysAbsent(0);
                attendanceReportMap.put(username,attendanceReport);

            }
            AttendanceReport attendanceReport=attendanceReportMap.get(username);
        }

        // Convert the attendance report map to a list

        List<AttendanceReport> attendanceReportList= new ArrayList<>(attendanceReportMap.values());

        return attendanceReportList;

    }




    @Override
    public Attendance save(AttendanceDto attendanceDto) {
        Attendance attendance= new Attendance();

        attendance.setUsername(attendanceDto.getUsername());
        attendance.setDate(attendanceDto.getDate());

        Employee  usernameInDb = employeeRepo.findEmployeeByUsername((attendance.getUsername()));

        if (usernameInDb==null){
            throw new GlobalExceptions("username doesn't exists. Please try again");
        }

        if (attendance.getUsername()!=null && attendance.getDate()!= null){
            Attendance existingAttendance = empAttendanceRepo.findByUsernameAndDate(attendance.getUsername(), attendance.getDate());

        if (existingAttendance!=null ){

            throw new GlobalExceptions("you have done your attendance");
        }

        else {
            Attendance latestAttendance= empAttendanceRepo.findByUsernameOrderByDateDesc(attendance.getUsername(),
                    attendance.getDate());
            if (latestAttendance!=null && attendance.getDate().isBefore(latestAttendance.getDate())){

                throw new GlobalExceptions("You cannot do attendance before latest attendance date");
            }

            attendance.setStatus(AttendanceStatus.PRESENT);
        }

        }
        System.out.println(attendanceDto.getDate());
        System.out.println(attendance.getDate());
        return empAttendanceRepo.save(attendance);
    }

    @Override
    public List<Attendance> findAll() {
        return empAttendanceRepo.findAll();
    }

    @Override
    public List<Attendance> absentEmployee(String id) {

        return null;
    }

    @Override
    public List<Attendance> findByUsername(String username) {

        Employee employee= employeeRepo.findByUsername(username);
        if (Objects.isNull(employee)){
            throw new GlobalExceptions("Username "+username+"is not present");
        }

        return null;
    }

    @Override
    public List<AttendanceReport> findAttendanceByUsername(String username) {
        Employee employee= new Employee();
//       List <AttendanceReport> attendanceReport= empAttendanceRepo.findAttendanceByUsername(username);

        return null;
    }


}
