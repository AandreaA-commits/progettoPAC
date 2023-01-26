package it.centrosport.webserver.event;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface EventControllerIF {
	public Iterable<Event> getEvents();
	public Event getEvent(@PathVariable String eventId);
	public void deleteEvent(@PathVariable("eventId") String eventId);
	public Event createEvent(@RequestBody EventDto eventDto);
}
