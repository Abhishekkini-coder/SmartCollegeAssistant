package pkg1.Controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.student.Student;
import pkg1.Service.student.StudentService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Student student) {
        boolean isValid = studentService.validateLogin(student.getEmail(), student.getPassword());
        if (isValid) {
            // Optional: Add token generation logic here
            return ResponseEntity.ok().body(Map.of("message", "Login successful", "token", "dummyToken"));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
    }


    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
}
