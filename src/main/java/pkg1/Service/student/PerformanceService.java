package pkg1.Service.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg1.Entity.student.Attendance;
import pkg1.Entity.student.AttendanceRepo;
import pkg1.Entity.student.Grade;
import pkg1.Entity.student.GradeRepo;
import pkg1.Entity.student.Student;
import pkg1.Entity.student.ToDo;
import pkg1.Entity.student.ToDoRepo;

@Service
public class PerformanceService {

    @Autowired
    private GradeRepo gradeRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private ToDoRepo toDoRepo;

    public double getAverageGrade(Student student) {
        List<Grade> grades = gradeRepo.findByStudent(student);
        if (grades.isEmpty()) return 0.0;

        double total = 0;
        for (Grade g : grades) total += g.getMarksObtained();
        return total / grades.size();
    }

    public double getAttendancePercentage(Student student) {
        List<Attendance> records = attendanceRepo.findByStudent(student);
        if (records.isEmpty()) return 0.0;

        long present = records.stream().filter(a -> a.getStatus().equalsIgnoreCase("Present")).count();
        return (present * 100.0) / records.size();
    }

    public double getTaskCompletionRate(Student student) {
        Optional<ToDo> tasks = toDoRepo.findByStudent(student);
        if (tasks.isEmpty()) return 0.0;

        long completed = tasks.stream().filter(ToDo::isCompleted).count();
        return (completed * 100.0) / tasks.size();
    }
}
