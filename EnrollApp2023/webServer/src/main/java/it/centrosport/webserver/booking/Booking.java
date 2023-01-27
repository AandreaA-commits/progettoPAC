package it.centrosport.webserver.booking;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Booking {

	@Id
	private String idBooking;

	private String idCampoPrenotato;
	private String idUtentePrenotazione;
	private Date dataPrenotazione;
	private int nPostiPrenotati;

	public Booking(String idCampoPrenotato, String idUtentePrenotazione, Date dataPrenotazione, int nPostiPrenotati) {
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

	public Date getDate() {
		return dataPrenotazione;
	}

	public void setDate(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public int getPostiPrenotati() {
		return nPostiPrenotati;
	}

	public void setPostiPrenotati(int nPostiPrenotati) {
		this.nPostiPrenotati = nPostiPrenotati;
	}
	
	

}
