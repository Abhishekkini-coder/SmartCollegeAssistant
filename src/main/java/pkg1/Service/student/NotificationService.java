package pkg1.Service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg1.Entity.student.Notification;
import pkg1.Entity.student.NotificationRepo;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    /** Fetch all notifications for a student, newest first */
    public List<Notification> getNotificationsForStudent(Long studentId) {
        return notificationRepo.findByStudentIdOrderByTimestampDesc(studentId);
    }

    /** Create a new notification (defaults to unread) */
    public Notification addNotification(Notification notification) {
        notification.setRead(false);
        return notificationRepo.save(notification);
    }

    /** Mark an existing notification as read */
    public Notification markNotificationAsRead(Long notifId) {
        Notification n = notificationRepo.findById(notifId)
            .orElseThrow(() -> new RuntimeException("Notification not found: " + notifId));
        n.setRead(true);
        return notificationRepo.save(n);
    }
}
