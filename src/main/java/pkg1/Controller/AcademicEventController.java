package pkg1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.AcademicEvent;
import pkg1.Service.AcademicEventService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*")
public class AcademicEventController {

    @Autowired
    private AcademicEventService eventService;

    @PostMapping("/add")
    public AcademicEvent addEvent(@RequestBody AcademicEvent event) {
        return eventService.addEvent(event);
    }

    @GetMapping("/between")
    public List<AcademicEvent> getEventsBetween(@RequestParam("start") String start,
                                                @RequestParam("end") String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return eventService.getEventsBetween(startDate, endDate);
    }
}
