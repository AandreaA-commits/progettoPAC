package it.centrosport.webserver.booking;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public class BookingDto {
	@NotBlank
	private String idCampoPrenotato;
	@NotBlank
	private String idUtentePrenotazione;
	@NotBlank
	private String emailUtente;
	
	@NotBlank
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Rome")
	private LocalDateTime datePrenotazione;
	@NotBlank
	private int nPostiPrenotati;

	public String getIdCampoPrenotato() {
		return idCampoPrenotato;
	}

	public void setIdCampoPrenotato(String idCampoPrenotato) {
		this.idCampoPrenotato = idCampoPrenotato;
	}
	
	public String getEmailUtente() {
		return emailUtente; 
	}
	
	public void setEmailUtente(String emailUtente) {
		this.emailUtente=emailUtente; 
	}

	public String getIdUtentePrenotazione() {
		return idUtentePrenotazione;
	}

	public void setIdUtentePrenotazione(String idUtentePrenotazione) {
		this.idUtentePrenotazione = idUtentePrenotazione;
	}

	public LocalDateTime getDatePrenotazione() {
		return datePrenotazione;
	}

	public void setDatePrenotazione(LocalDateTime datePrenotazione) {
		this.datePrenotazione = datePrenotazione;
	}

	public int getPostiPrenotati() {
		return nPostiPrenotati;
	}

	public void setPostiPrenotati(int nPostiPrenotati) {
		this.nPostiPrenotati = nPostiPrenotati;
	}
	
	


}
