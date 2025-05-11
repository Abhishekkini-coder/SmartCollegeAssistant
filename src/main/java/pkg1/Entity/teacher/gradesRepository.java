package pkg1.Entity.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface gradesRepository extends JpaRepository<grades, Long> {
    List<grades> findByTeacherId(Long teacherId);
    List<grades> findByStudentId(Long studentId);
}
