package pkg1.Entity.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudent(Student student);

    @Query("SELECT a.subject, " +
           "       COUNT(CASE WHEN a.status = 'Present' THEN 1 END) * 100.0 / COUNT(a) " +
           "FROM Attendance a " +
           "WHERE a.student.id = :studentId " +
           "GROUP BY a.subject")
    List<Object[]> findAttendancePercentageByStudentId(@Param("studentId") Long studentId);
}
