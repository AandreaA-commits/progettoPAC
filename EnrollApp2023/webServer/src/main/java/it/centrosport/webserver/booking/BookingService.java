package it.centrosport.webserver.booking;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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
		Optional<Booking> campo = bookingRepository.findByidCampoPrenotatoAndDatePrenotazione(booking.getIdCampoPrenotato(), booking.getDatePrenotazione());
		if(campo.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Campo già prenotato");
		
		//inserimento invio email di conferma
		SimpleMailMessage sms = new SimpleMailMessage();
		sms.setFrom("ariciandrea1999@gmail.com");
		sms.setTo(booking.getEmailUtente());
		//sms.setTo("ariciandrea1999@gmail.com");
		//sms.setTo("arici.andrea.99@gmail.com");
		sms.setSubject("Conferma prenotazione NewSport S.R.L.");
		
		String corpo_sms = "Volevamo infomarla che la sua prenotazione ha avuto esito positivo \n"+ 
				"Campo prenotato per il giorno: " + booking.getDatePrenotazione() + "\n" +
				"ID utente che ha effettuato la prenotazione: "+ booking.getIdUtentePrenotazione() + "\n" + 
				"Prenotazione effettuata per " + booking.getPostiPrenotati() + "giocatori \n" + 
				"Cordiali saluti\n Il Team di NewSport S.R.L ";
		sms.setText(corpo_sms);
		emailSender.send(sms);
		
		return bookingRepository.save(booking);
	}
	
	public void deleteBooking(String id) {
		Optional<Booking> bookingToDelete = bookingRepository.findById(id);
		if(!bookingToDelete.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID non presente");
		
		//inserimento invio email di conferma
				SimpleMailMessage sms = new SimpleMailMessage();
				sms.setFrom("ariciandrea1999@gmail.com");
				sms.setTo(bookingToDelete.get().getEmailUtente());
				//sms.setTo("ariciandrea1999@gmail.com");
				//sms.setTo("arici.andrea.99@gmail.com");
				sms.setSubject("Cancellazione prenotazione NewSport S.R.L.");
				
				String corpo_sms = "Volevamo infomarla che la sua prenotazione è stata cancellata con successo \n"+ 
						"Campo prenotato per il giorno: " + bookingToDelete.get().getDatePrenotazione() + "\n" +
						"ID utente che ha effettuato la prenotazione: "+ bookingToDelete.get().getIdUtentePrenotazione() + "\n" + 
						"ID prenotazione: " + id + "\n"+
						"Cordiali saluti\n Il Team di NewSport S.R.L ";
				sms.setText(corpo_sms);
				emailSender.send(sms);
		
		
		bookingRepository.delete(bookingToDelete.get());
			
	}
}
