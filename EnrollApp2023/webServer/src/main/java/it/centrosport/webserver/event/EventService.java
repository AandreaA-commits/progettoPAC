package it.centrosport.webserver.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService implements EventServiceIF {

	private final static int MAX_TEAM = 5;
	private final EventRepositoryIF eventRepository;
	private final EventEnrollmentRepositoryIF eventEnrollmentRepository;
	
	@Autowired
	public EventService(EventRepositoryIF eventRepository, EventEnrollmentRepositoryIF eventEnrollmentRepository) {
		this.eventRepository = eventRepository;
		this.eventEnrollmentRepository = eventEnrollmentRepository;
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

	public EventEnrollment createEventEnrollment(EventEnrollment eventEnrollment) {
		Optional<Event> event = eventRepository.findById(eventEnrollment.getIdEvent());
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento non esistente");
		int nPlayers = 0;
		ArrayList<EventEnrollment> p = event.get().getPlayers();
		for (EventEnrollment e : p) {
			nPlayers += e.getNumIscritti();
		}
		if(nPlayers >= event.get().getMaxPlayers()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Iscrizioni massime raggiunte");
		if(eventEnrollment.getNumIscritti() > MAX_TEAM) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Troppi giocatori!");
		event.get().addPlayers(eventEnrollment);
		eventRepository.save(event.get());
		return eventEnrollmentRepository.save(eventEnrollment);
	}
	
	public ArrayList<EventEnrollment> getPlayers(String id) {
		Optional<Event> event = eventRepository.findById(id);
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		
		return event.get().getPlayers();
	}
}
