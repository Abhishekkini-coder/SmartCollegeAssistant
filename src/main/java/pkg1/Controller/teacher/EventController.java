package pkg1.Controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pkg1.Entity.teacher.Event;
import pkg1.Service.teacher.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }


    @GetMapping("/{teacherId}")
    public List<Event> getEventsByTeacher(@PathVariable Long teacherId) {
        return eventService.getEventsByTeacherId(teacherId);
    }

}
