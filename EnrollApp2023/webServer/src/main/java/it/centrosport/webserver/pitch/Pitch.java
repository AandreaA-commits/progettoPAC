package it.centrosport.webserver.pitch;

import org.springframework.data.annotation.Id;


public class Pitch {
	
	@Id
	private String current_id;
	
	private String location;
	private String name;
	
	public Pitch() {}
	
	public Pitch(String location, String name) {
		this.location = location;
		this.name = name;
		this.current_id= "abcd";
	}
	
	public String getId() {
		return current_id;
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
		return String.format("Pitch[id=%s, location=%s, name=%s]", current_id, name, location);
	}
	
	
	
}
