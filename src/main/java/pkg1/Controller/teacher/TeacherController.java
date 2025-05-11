package pkg1.Controller.teacher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pkg1.Entity.teacher.Teacher;
import pkg1.Service.teacher.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody Teacher t) {
        return ResponseEntity.ok(service.create(t));
    }

  
    @GetMapping
    public ResponseEntity<List<Teacher>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(
            @PathVariable Long id,
            @RequestBody Teacher t) {
        return ResponseEntity.ok(service.update(id, t));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
