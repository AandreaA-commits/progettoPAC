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
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Rome")
	private LocalDateTime dataPrenotazione;
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
