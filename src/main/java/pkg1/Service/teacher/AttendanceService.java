package pkg1.Service.teacher;

import org.springframework.stereotype.Service;
import pkg1.Entity.teacher.Attendance;
import pkg1.Entity.teacher.AttendanceRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("teacherAttendanceService")

public class AttendanceService {
    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) {
        this.repo = repo;
    }

    /** Mark or record attendance */
    public Attendance create(Attendance a) {
        return repo.save(a);
    }

    /** List all attendance records for a teacher */
    public List<Attendance> listByTeacher(Long teacherId) {
        return repo.findByTeacherId(teacherId);
    }

    /** List attendance for a teacher on a specific date */
    public List<Attendance> listByTeacherAndDate(Long teacherId, LocalDate date) {
        return repo.findByTeacherIdAndDate(teacherId, date);
    }

    /** Update an attendance record */
    public Attendance update(Long id, Attendance updated) {
        Attendance existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Attendance not found with id " + id));
        existing.setStudentId(updated.getStudentId());
        existing.setDate(updated.getDate());
        existing.setPresent(updated.isPresent());
        return repo.save(existing);
    }

    /** Delete an attendance record */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cannot delete; attendance not found with id " + id);
        }
        repo.deleteById(id);
    }
}
