package it.centrosport.webserver.booking;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Booking {

	@Id
	private String idBooking;

	private String idCampoPrenotato;
	private String idUtentePrenotazione;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Rome")
	private LocalDateTime dataPrenotazione;
	private int nPostiPrenotati;

	public Booking(String idCampoPrenotato, String idUtentePrenotazione, LocalDateTime dataPrenotazione, int nPostiPrenotati) {
		this.idCampoPrenotato = idCampoPrenotato;
		this.idUtentePrenotazione = idUtentePrenotazione;
		this.dataPrenotazione = dataPrenotazione;
		this.nPostiPrenotati = nPostiPrenotati;
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

	public String getIdUtente() {
		return idUtentePrenotazione;
	}

	public void setIdUtente(String idUtentePrenotazione) {
		this.idUtentePrenotazione = idUtentePrenotazione;
	}

	public LocalDateTime getDate() {
		return dataPrenotazione;
	}

	public void setDate(LocalDateTime dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public int getPostiPrenotati() {
		return nPostiPrenotati;
	}

	public void setPostiPrenotati(int nPostiPrenotati) {
		this.nPostiPrenotati = nPostiPrenotati;
	}
	
	

}
