package it.centrosport.webserver.event;

import jakarta.validation.constraints.NotBlank;

public class EventEnrollmentDto {

	@NotBlank
	private String idEvent;
	@NotBlank
	private String idUtenteIscrizione;
	@NotBlank
	private int numIscritti;
	
	public String getIdUtenteIscrizione() {
		return idUtenteIscrizione;
	}
	public void setIdUtenteIscrizione(String idUtenteIscrizione) {
		this.idUtenteIscrizione = idUtenteIscrizione;
	}
	public int getNumIscritti() {
		return numIscritti;
	}
	public void setNumIscritti(int numIscritti) {
		this.numIscritti = numIscritti;
	}
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}
	
}
