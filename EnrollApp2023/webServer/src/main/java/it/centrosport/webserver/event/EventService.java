package it.centrosport.webserver.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

	private final EventRepository eventRepository;
	
	@Autowired
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	public Event getEvent(String id) {
		Optional<Event> event = eventRepository.findById(id);
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		
		return event.get();
	}
	
	public List<Event> getEvents() {
		return eventRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Event createEvent(Event event) {
		return eventRepository.save(event);
	}
	
	public void deleteEvent(String id) {
		Optional<Event> eventToDelete = eventRepository.findById(id);
		if(!eventToDelete.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID non presente");
		eventRepository.delete(eventToDelete.get());
		
	}
}
