package pkg1.Service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkg1.Entity.teacher.Event;
import pkg1.Entity.teacher.EventRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEventsByTeacherId(Long teacherId) {
        return eventRepository.findByTeacherId(teacherId);
    }

	public List<Event> getEventsByTeacherId1(Long teacherId) {
		// TODO Auto-generated method stub
		return null;
	}
}
