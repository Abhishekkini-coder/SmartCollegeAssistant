package pkg1.Entity.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // You can add custom queries here if needed, e.g. findByEmail(...)
}
