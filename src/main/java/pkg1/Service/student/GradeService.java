package pkg1.Service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg1.Entity.student.Grade;
import pkg1.Entity.student.GradeRepo;

@Service
public class GradeService {

    @Autowired
    private GradeRepo gradeRepo;

    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepo.findByStudentId(studentId);
    }

    public Grade addGrade(Grade grade) {
        return gradeRepo.save(grade);
    }
}
