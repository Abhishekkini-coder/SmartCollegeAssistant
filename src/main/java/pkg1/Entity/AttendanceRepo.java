package pkg1.Entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepo extends JpaRepository<Attendance, Long>{
	List<Attendance> findByStudent(Student student);
}
