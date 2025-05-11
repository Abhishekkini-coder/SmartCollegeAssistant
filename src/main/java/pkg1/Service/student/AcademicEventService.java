package pkg1.Service.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg1.Entity.student.AcademicEvent;
import pkg1.Entity.student.AcademicEventRepo;

@Service
public class AcademicEventService {

    @Autowired
    private AcademicEventRepo eventRepo;

    public List<AcademicEvent> getEventsBetween(LocalDate start, LocalDate end) {
        return eventRepo.findByDateBetween(start, end);
    }


    public AcademicEvent addEvent(AcademicEvent event) {
        return eventRepo.save(event);
    }
}