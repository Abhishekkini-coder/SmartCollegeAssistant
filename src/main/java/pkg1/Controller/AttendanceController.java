package pkg1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.Attendance;
import pkg1.Service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @GetMapping("/student/{id}")
    public List<Attendance> getAttendance(@PathVariable Long id) {
        return attendanceService.getAttendanceByStudentId(id);
    }
}
