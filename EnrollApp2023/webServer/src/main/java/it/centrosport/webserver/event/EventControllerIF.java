package it.centrosport.webserver.event;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface EventControllerIF {

	public Iterable<Event> getEvents();

	public Event getEvent(@PathVariable String eventId);

	public void deleteEvent(@PathVariable("eventId") String eventId);

	public Event createEvent(@RequestBody EventDto eventDto);
	
	public EventEnrollment createEventEnrollment(@RequestBody EventEnrollmentDto eventEnrollmentDto);
	
	public void deleteEventEnrollment(@PathVariable("eventEnrollmentId")String eventEnrollmentId);
}
