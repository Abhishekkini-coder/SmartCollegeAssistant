package pkg1.Controller.teacher;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.teacher.Assignment;
import pkg1.Service.teacher.AssignmentService;

@RestController
@RequestMapping("/api/teacher/assignments")
public class AssignmentController {
    private final AssignmentService service;

    public AssignmentController(AssignmentService service) {
        this.service = service;
    }

    /** POST   /api/teacher/assignments
     *  Body: JSON Assignment
     */
    @PostMapping
    public ResponseEntity<Assignment> create(@RequestBody Assignment assignment) {
        Assignment saved = service.create(assignment);
        return ResponseEntity.ok(saved);
    }

    /** GET    /api/teacher/assignments/by-teacher/{tid} */
    @GetMapping("/by-teacher/{tid}")
    public ResponseEntity<List<Assignment>> listByTeacher(@PathVariable("tid") Long teacherId) {
        List<Assignment> list = service.listByTeacher(teacherId);
        return ResponseEntity.ok(list);
    }

    /** PUT    /api/teacher/assignments/{id}
     *  Body: JSON Assignment
     */
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> update(
            @PathVariable Long id,
            @RequestBody Assignment assignment) {
        Assignment updated = service.update(id, assignment);
        return ResponseEntity.ok(updated);
    }

    /** DELETE /api/teacher/assignments/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
