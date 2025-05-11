package pkg1.Controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.student.Attendance;
import pkg1.Service.student.AttendanceService;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;

@Controller("studentAttendanceController")
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
    
    @GetMapping("/percentage/{studentId}")
    public List<Map<String, Object>> getAttendancePercentage(@PathVariable Long studentId) {
        return attendanceService.getAttendancePercentageByStudentId(studentId);
    }
    
    @GetMapping("/student/{id}/attendance-percentage")
    public ResponseEntity<List<Map<String, Object>>> getAttendancePercentage1(@PathVariable Long id) {
        List<Map<String, Object>> data = attendanceService.getAttendancePercentageByStudentId(id);
        return ResponseEntity.ok(data);
    }


}
