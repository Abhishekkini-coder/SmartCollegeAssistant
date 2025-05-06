package pkg1.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg1.Entity.Notification;
import pkg1.Entity.NotificationRepo;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    public Optional<Notification> getNotificationsForStudent(Long studentId) {
        return notificationRepo.findById(studentId);
    }

    public Notification addNotification(Notification notification) {
        return notificationRepo.save(notification);
    }
}
