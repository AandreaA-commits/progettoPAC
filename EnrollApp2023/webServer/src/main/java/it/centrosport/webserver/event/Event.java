package it.centrosport.webserver.event;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Event {

	@Id
	private String current_id;
	
	private String name;
	private String location;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Rome")
	private LocalDateTime dateTime;
	
	public Event() {}
	
	public Event(String name, String location, LocalDateTime dateTime) {
		this.name = name;
		this.location = location;
		this.dateTime = dateTime;
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
}
