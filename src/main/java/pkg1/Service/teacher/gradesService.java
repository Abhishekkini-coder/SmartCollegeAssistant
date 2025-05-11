package pkg1.Service.teacher;

import org.springframework.stereotype.Service;
import pkg1.Entity.teacher.grades;
import pkg1.Entity.teacher.gradesRepository;

import java.util.List;

@Service
public class gradesService {
    private final gradesRepository repo;

    public gradesService(gradesRepository repo) {
        this.repo = repo;
    }

    /** Add a new grade */
    public grades create(grades g) {
        return repo.save(g);
    }

    /** List all grades given by a teacher */
    public List<grades> listByTeacher(Long teacherId) {
        return repo.findByTeacherId(teacherId);
    }

    /** List all grades for a student */
    public List<grades> listByStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    /** Update a grade record */
    public grades update(Long id, grades updated) {
        grades existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Grade not found with id " + id));
        existing.setSubject(updated.getSubject());
        existing.setScore(updated.getScore());
        existing.setStudentId(updated.getStudentId());
        existing.setTeacherId(updated.getTeacherId());
        return repo.save(existing);
    }

    /** Delete a grade record */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cannot delete; grade not found with id " + id);
        }
        repo.deleteById(id);
    }
}
