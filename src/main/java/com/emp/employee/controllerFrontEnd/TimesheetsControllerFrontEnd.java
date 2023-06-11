package com.emp.employee.controllerFrontEnd;

import com.emp.employee.dto.TimesheetsDto;
import com.emp.employee.model.Timesheets;
import com.emp.employee.service.TimesheetsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/v2/employee")
public class TimesheetsControllerFrontEnd {

    private final TimesheetsService timesheetsService;

    @ModelAttribute("timesheet")
    public TimesheetsDto timesheet(){
        return  new TimesheetsDto();
    }
    @GetMapping("/timesheet")
    public String empTimesheet(Model model){
        TimesheetsDto timesheets = new TimesheetsDto();
        model.addAttribute("empTimesheet", timesheets);
        return "timesheet";
    }

    @PostMapping("/save-timesheet")
    public String empTimesheet(@ModelAttribute TimesheetsDto timesheetsDto){
        Timesheets  timesheets= timesheetsService.save(timesheetsDto);
        return "/timesheet";
    }

    @GetMapping("/list-timesheet")
    public String shoeTimesheetList(Model model){
        List<Timesheets> timesheets= timesheetsService.findAll();
        model.addAttribute("empTimesheetList",timesheets);
        return "timesheetList";
    }

}
