package it.centrosport.webserver.event;

import org.springframework.data.annotation.Id;

public class EventEnrollment {

	@Id
	private String idEnrollment;
	
	private String idUtenteIscrizione;
	private int numIscritti;
	
	public String getIdEnrollment() {
		return idEnrollment;
	}
	public void setIdEnrollment(String idEnrollment) {
		this.idEnrollment = idEnrollment;
	}
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
