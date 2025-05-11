package pkg1.Entity.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepo extends JpaRepository<Grade, Long> {
    // Find grades by the Student entity
    List<Grade> findByStudentId(Long studentId); // Use studentId directly here
}
