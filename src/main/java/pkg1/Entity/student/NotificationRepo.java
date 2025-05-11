package pkg1.Entity.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
    // Finds notifications for a student, ordered newest → oldest
    List<Notification> findByStudentIdOrderByTimestampDesc(Long studentId);
}
