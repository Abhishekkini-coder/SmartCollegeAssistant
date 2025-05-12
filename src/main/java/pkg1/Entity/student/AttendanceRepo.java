// src/main/java/pkg1/Entity/student/AttendanceRepo.java
package pkg1.Entity.student;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
    // Fetch all raw records for a given student
    List<Attendance> findByStudentId(Long studentId);
}
