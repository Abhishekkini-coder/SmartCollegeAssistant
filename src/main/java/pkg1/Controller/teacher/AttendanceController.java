package pkg1.Controller.teacher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkg1.Entity.teacher.Attendance;
import pkg1.Service.teacher.AttendanceService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;

@Controller("teacherAttendanceController")
@RestController
@RequestMapping("/api/teacher/attendance")
public class AttendanceController {
    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

   
    @PostMapping
    public ResponseEntity<Attendance> create(@RequestBody Attendance a) {
        Attendance saved = service.create(a);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-teacher/{tid}")
    public ResponseEntity<List<Attendance>> listByTeacher(@PathVariable Long tid) {
        return ResponseEntity.ok(service.listByTeacher(tid));
    }

  
    @GetMapping("/by-teacher/{tid}/date/{date}")
    public ResponseEntity<List<Attendance>> listByTeacherAndDate(
            @PathVariable Long tid,
            @PathVariable String date) {
        LocalDate ld = LocalDate.parse(date);
        return ResponseEntity.ok(service.listByTeacherAndDate(tid, ld));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Attendance> update(
            @PathVariable Long id,
            @RequestBody Attendance a) {
        return ResponseEntity.ok(service.update(id, a));
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
