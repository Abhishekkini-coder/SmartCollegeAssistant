package pkg1.Entity;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface GradeRepo extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
}
