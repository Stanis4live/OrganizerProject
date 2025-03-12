package spectr.java_group.OrganizerProject.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsByUser(Long userId){
        return eventRepository.findByUserId(userId);
    }

    public Optional<Event> getElementById(Long id){
        return  eventRepository.findById(id);
    }

    public void addEvent(Event event){
        eventRepository.save(event);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}
