package it.centrosport.webserver.booking;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Booking {

	@Id
	private String idBooking;

	private String emailUtente;
	private String idCampoPrenotato;
	private String idUtentePrenotazione;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Rome")
	private LocalDateTime datePrenotazione;
	private int nPostiPrenotati;

	public Booking(String idCampoPrenotato, String idUtentePrenotazione, LocalDateTime datePrenotazione, int nPostiPrenotati, String emailUtente) {
		this.idCampoPrenotato = idCampoPrenotato;
		this.idUtentePrenotazione = idUtentePrenotazione;
		this.datePrenotazione = datePrenotazione;
		this.nPostiPrenotati = nPostiPrenotati;
		this.emailUtente = emailUtente;
	}
	
	public Booking() {}

	public String getId() {
		return idBooking;
	}

	public void setId(String idBooking) {
		this.idBooking = idBooking;
	}

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
