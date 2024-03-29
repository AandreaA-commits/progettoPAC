package it.centrosport.webserver.event;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public class EventDto {

	@NotBlank
	private String name;
	@NotBlank
	private String location;
	@NotBlank
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Rome")
	private LocalDateTime dateTime;
	@NotBlank
	private int maxPlayers;
	
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

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
}
