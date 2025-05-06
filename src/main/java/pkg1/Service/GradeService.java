package pkg1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg1.Entity.Grade;
import pkg1.Entity.GradeRepo;
import pkg1.Entity.Student;

@Service
public class GradeService {

    @Autowired
    private GradeRepo gradeRepo;

    public List<Grade> getGradesByStudent(Student student) {
        return gradeRepo.findByStudent(student);
    }

    public Grade addGrade(Grade grade) {
        return gradeRepo.save(grade);
    }
}
