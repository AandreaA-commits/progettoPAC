package it.centrosport.webserver.event;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "events")
public class EventController implements EventControllerIF{
	private final EventService eventService;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	private Event dtoToEntity(EventDto eventDto) {
		var event = new Event();
		event.setLocation(eventDto.getLocation());
		event.setName(eventDto.getName());
		event.setDateTime(eventDto.getDateTime());
		event.setMaxPlayers(eventDto.getMaxPlayers());
		return event;
	}
	
	private EventEnrollment dtoToEntity(EventEnrollmentDto eventEnrollmentDto) {
		var eventEnrollment = new EventEnrollment();
		eventEnrollment.setIdUtenteIscrizione(eventEnrollmentDto.getIdUtenteIscrizione());
		eventEnrollment.setNumIscritti(eventEnrollmentDto.getNumIscritti());
		eventEnrollment.setIdEvent(eventEnrollmentDto.getIdEvent());
		return eventEnrollment;
	}
	
	@GetMapping
	public Iterable<Event> getEvents(){
		return eventService.getEvents();
	}
	
	@GetMapping("{eventId}")
	public Event getEvent(@PathVariable String eventId) {
		return eventService.getEvent(eventId);
	}
	
	@DeleteMapping("{eventId}")
	public void deleteEvent(@PathVariable("eventId") String eventId) {
		eventService.deleteEvent(eventId);
	}
	
	@PostMapping
	public Event createEvent(@RequestBody EventDto eventDto) {
		var event = dtoToEntity(eventDto);
		return eventService.createEvent(event);
	}
	
	@PostMapping("enroll")
	public EventEnrollment createEventEnrollment(@RequestBody EventEnrollmentDto eventEnrollmentDto) {
		var eventEnrollment = dtoToEntity(eventEnrollmentDto);
		return eventService.createEventEnrollment(eventEnrollment);
	}
	
	@GetMapping("players/{eventId}")
	public ArrayList<EventEnrollment> getPlayers(@PathVariable String eventId) {
		return eventService.getEvent(eventId).getPlayers();
	}
	
	@GetMapping("teams/{eventId}")
	public ArrayList<ArrayList<String>> getTeams(@PathVariable String eventId) {
		return eventService.getTeams(eventId);
	}

	@DeleteMapping("enroll/{eventEnrollmentId}")
	public void deleteEventEnrollment(String eventEnrollmentId) {
		eventService.deleteEventEnrollment(eventEnrollmentId);
	}
}
