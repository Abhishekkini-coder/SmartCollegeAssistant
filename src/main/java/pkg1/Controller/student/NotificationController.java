package pkg1.Controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.student.Notification;
import pkg1.Service.student.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/add")
    public Notification addNotification(@RequestBody Notification notification) {
        return notificationService.addNotification(notification);
    }

    /** Fetch all notifications for a given student */
    @GetMapping("/student/{id}")
    public List<Notification> getNotifications(@PathVariable Long id) {
        return notificationService.getNotificationsForStudent(id);
    }

    /** Mark a single notification as read */
    @PutMapping("/markRead/{notifId}")
    public Notification markAsRead(@PathVariable Long notifId) {
        return notificationService.markNotificationAsRead(notifId);
    }
}

