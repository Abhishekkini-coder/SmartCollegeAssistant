package pkg1.Entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long>{
	 List<Notification> findByStudentOrderByTimestampDesc(Student student);
}
