package it.centrosport.webserver.booking;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class BookingDto {
	@NotBlank
	private String idCampoPrenotato;
	@NotBlank
	private String idUtentePrenotazione;
	@NotBlank
	private Date dataPrenotazione;
	@NotBlank
	private int nPostiPrenotati;

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
