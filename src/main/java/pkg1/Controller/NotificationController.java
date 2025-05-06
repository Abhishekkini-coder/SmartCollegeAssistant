package pkg1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.Notification;
import pkg1.Service.NotificationService;


import java.util.Optional;

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

    @GetMapping("/student/{id}")
    public Optional<Notification> getNotifications(@PathVariable Long id) {
        return notificationService.getNotificationsForStudent(id);
    }
}
