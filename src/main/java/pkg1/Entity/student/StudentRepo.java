package pkg1.Entity.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    
    // Case-insensitive email search
    Optional<Student> findByEmailIgnoreCase(String email);
}
