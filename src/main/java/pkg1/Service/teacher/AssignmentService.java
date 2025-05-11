package pkg1.Service.teacher;

import java.util.List;

import org.springframework.stereotype.Service;

import pkg1.Entity.teacher.Assignment;
import pkg1.Entity.teacher.AssignmentRepository;

@Service
public class AssignmentService {
    private final AssignmentRepository repo;

    public AssignmentService(AssignmentRepository repo) {
        this.repo = repo;
    }

    /** Create a new assignment */
    public Assignment create(Assignment assignment) {
        return repo.save(assignment);
    }

    /** List all assignments for a given teacher */
    public List<Assignment> listByTeacher(Long teacherId) {
        return repo.findByTeacherId(teacherId);
    }

    /** Update an existing assignment (throws if not found) */
    public Assignment update(Long id, Assignment updated) {
        Assignment existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Assignment not found with id " + id));
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        // if you allow changing teacherId:
        existing.setTeacherId(updated.getTeacherId());
        return repo.save(existing);
    }

    /** Delete an assignment by id */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cannot delete; assignment not found with id " + id);
        }
        repo.deleteById(id);
    }
}
