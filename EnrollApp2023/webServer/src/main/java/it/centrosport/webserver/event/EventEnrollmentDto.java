package it.centrosport.webserver.event;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public class EventEnrollmentDto {

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
	
	
}
