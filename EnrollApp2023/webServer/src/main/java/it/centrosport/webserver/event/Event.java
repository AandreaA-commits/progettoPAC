package it.centrosport.webserver.event;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Event {

	@Id
	private String current_id;
	
	private String name;
	private String location;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Rome")
	private LocalDateTime dateTime;
	private ArrayList<EventEnrollment> players = new ArrayList<EventEnrollment>();
	private int maxPlayers;
	
	public Event() {}
	
	public Event(String name, String location, LocalDateTime dateTime, ArrayList<EventEnrollment> players, int maxPlayers) {
		this.name = name;
		this.location = location;
		this.dateTime = dateTime;
		this.players = players;
		this.maxPlayers = maxPlayers;
	}

	public String getId() {
		return current_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}	

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String toString() {
		return String.format("Event[id=%s, name=%s, location=%s, data e ora=%s]", current_id, name, location, dateTime);
	}
	
	public ArrayList<EventEnrollment> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<EventEnrollment> players) {
		this.players = players;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
}
