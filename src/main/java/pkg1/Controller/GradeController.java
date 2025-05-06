package pkg1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.Grade;
import pkg1.Entity.Student;
import pkg1.Service.GradeService;

import java.util.List;

@RestController
@RequestMapping("/grades")
@CrossOrigin(origins = "*")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("/add")
    public Grade addGrade(@RequestBody Grade grade) {
        return gradeService.addGrade(grade);
    }

    @PostMapping("/student")
    public List<Grade> getGradesByStudent(@RequestBody Student student) {
        return gradeService.getGradesByStudent(student);
    }
}
