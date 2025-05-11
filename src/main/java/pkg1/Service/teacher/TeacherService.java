package pkg1.Service.teacher;

import org.springframework.stereotype.Service;
import pkg1.Entity.teacher.Teacher;
import pkg1.Entity.teacher.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository repo;

    public TeacherService(TeacherRepository repo) {
        this.repo = repo;
    }

    /** Create a new teacher */
    public Teacher create(Teacher t) {
        return repo.save(t);
    }

    /** List all teachers */
    public List<Teacher> listAll() {
        return repo.findAll();
    }

    /** Get a single teacher by id */
    public Teacher getById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Teacher not found with id " + id));
    }

    /** Update teacher details */
    public Teacher update(Long id, Teacher updated) {
        Teacher existing = getById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        return repo.save(existing);
    }

    /** Delete a teacher */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cannot delete; teacher not found with id " + id);
        }
        repo.deleteById(id);
    }
}
