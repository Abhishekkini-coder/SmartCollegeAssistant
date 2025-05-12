package pkg1.Controller.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkg1.Entity.student.Attendance;
import pkg1.Service.student.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /** GET /attendance/student/{id} → raw Attendance list */
    @GetMapping("/student/{id}")
    public ResponseEntity<List<Attendance>> getAttendance(@PathVariable Long id) {
        List<Attendance> list = attendanceService.getAttendanceByStudentId(id);
        return ResponseEntity.ok(list);
    }

    /** POST /attendance/mark to mark new records (unchanged) */
    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }
}
