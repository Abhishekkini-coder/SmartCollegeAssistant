package pkg1.Controller.teacher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkg1.Entity.teacher.grades;
import pkg1.Service.teacher.gradesService;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/grades")
public class gradesController {
    private final gradesService service;

    public gradesController(gradesService service) {
        this.service = service;
    }

    
    @PostMapping
    public ResponseEntity<grades> create(@RequestBody grades g) {
        return ResponseEntity.ok(service.create(g));
    }

    
    @GetMapping("/by-teacher/{tid}")
    public ResponseEntity<List<grades>> listByTeacher(@PathVariable Long tid) {
        return ResponseEntity.ok(service.listByTeacher(tid));
    }

   
    @GetMapping("/by-student/{sid}")
    public ResponseEntity<List<grades>> listByStudent(@PathVariable("sid") Long studentId) {
        return ResponseEntity.ok(service.listByStudent(studentId));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<grades> update(
            @PathVariable Long id,
            @RequestBody grades g) {
        return ResponseEntity.ok(service.update(id, g));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
