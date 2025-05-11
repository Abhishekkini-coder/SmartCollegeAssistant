package pkg1.Entity.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByTeacherId(Long teacherId);
    List<Attendance> findByTeacherIdAndDate(Long teacherId, LocalDate date);
}
