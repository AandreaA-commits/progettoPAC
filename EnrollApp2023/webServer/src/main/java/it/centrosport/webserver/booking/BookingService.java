package it.centrosport.webserver.booking;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService implements BookingServiceIF{
	
	private final BookingRepositoryIF bookingRepository;
	
	@Autowired
	public BookingService(BookingRepositoryIF bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	@Autowired
    private JavaMailSender emailSender;
	
	public Booking getBooking(String id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		if(!booking.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		
		return booking.get();
	}
	
	public List<Booking> getBookings() {
		return bookingRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Booking createBooking(Booking booking) {
		List<Booking> bookings = bookingRepository.findAll();
		
		for(int i=0; i<bookings.size(); i++) {
			LocalDateTime temp = bookings.get(i).getDatePrenotazione().plusHours(1);
			if(bookings.get(i).getIdCampoPrenotato().equals(booking.getIdCampoPrenotato()) &&
					booking.getDatePrenotazione().isBefore(temp) && booking.getDatePrenotazione().isAfter(bookings.get(i).getDatePrenotazione())){
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Campo già prenotato");
				
			}
		}
		
		//parametri per l'invio della mail di conferma
		SimpleMailMessage sms = new SimpleMailMessage();
		sms.setFrom("newsportsrl.communications@gmail.com");
		sms.setTo(booking.getEmailUtente());
		sms.setSubject("Conferma prenotazione NewSport S.R.L.");
		
		//creazione del corpo del messagio
		String corpo_sms = "Volevamo infomarla che la sua prenotazione ha avuto esito positivo \n"+ 
				"Campo prenotato per il giorno: " + booking.getDatePrenotazione() + "\n" +
				"ID utente che ha effettuato la prenotazione: "+ booking.getIdUtentePrenotazione() + "\n" + 
				"Prenotazione effettuata per " + booking.getPostiPrenotati() + "giocatori \n" + 
				"Cordiali saluti\n Il Team di NewSport S.R.L ";
		sms.setText(corpo_sms);
		emailSender.send(sms); //invio dell'email
		
		return bookingRepository.save(booking);
	}
	
	public void deleteBooking(String id) {
		Optional<Booking> bookingToDelete = bookingRepository.findById(id);
		if(!bookingToDelete.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID non presente");
		
		//impostazione parametri per invio di email di corretta eliminazione all'utente
				SimpleMailMessage sms = new SimpleMailMessage();
				sms.setFrom("newsportsrl.communications@gmail.com");
				sms.setTo(bookingToDelete.get().getEmailUtente());
				sms.setSubject("Cancellazione prenotazione NewSport S.R.L.");
				
				//creazione del corpo del messaggio
				String corpo_sms = "Volevamo infomarla che la sua prenotazione è stata cancellata con successo \n"+ 
						"Campo prenotato per il giorno: " + bookingToDelete.get().getDatePrenotazione() + "\n" +
						"ID utente che ha effettuato la prenotazione: "+ bookingToDelete.get().getIdUtentePrenotazione() + "\n" + 
						"ID prenotazione: " + id + "\n"+
						"Cordiali saluti\n Il Team di NewSport S.R.L ";
				sms.setText(corpo_sms);
				emailSender.send(sms); //invio del'email
		
		
		bookingRepository.delete(bookingToDelete.get());
			
	}
}
