package it.centrosport.webserver.pitch;

import org.springframework.data.annotation.Id;


public class Pitch {
	
	@Id
	private String id;
	
	private String location;
	private String name;
	
	public Pitch() {}
	
	public Pitch(String location, String name) {
		this.location = location;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return String.format("Pitch[id=%s, location=%s, name=%s]", id, name, location);
	}
	
	
	
}
