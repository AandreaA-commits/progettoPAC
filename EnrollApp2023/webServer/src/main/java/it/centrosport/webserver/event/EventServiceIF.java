package it.centrosport.webserver.event;

import java.util.ArrayList;
import java.util.List;

public interface EventServiceIF {

	public Event getEvent(String id);

	public List<Event> getEvents();

	public Event createEvent(Event event);

	public void deleteEvent(String id);
	
	public ArrayList<ArrayList<String>> getTeams(String idEvent);
}
