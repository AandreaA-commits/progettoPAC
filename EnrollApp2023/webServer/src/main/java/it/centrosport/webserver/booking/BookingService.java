package it.centrosport.webserver.booking;

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
	
	public Booking getBooking(String id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		if(!booking.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		
		return booking.get();
	}
	
	public List<Booking> getBookings() {
		return bookingRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Booking createBooking(Booking booking) {
		Optional<Booking> campo = bookingRepository.findByidCampoPrenotatoAndDataPrenotazione(booking.getIdCampoPrenotato(),booking.getDate());
		if(campo.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Campo già prenotato");
		return bookingRepository.save(booking);
	}
	
	public void deleteBooking(String id) {
		Optional<Booking> bookingToDelete = bookingRepository.findById(id);
		if(!bookingToDelete.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID non presente");
		bookingRepository.delete(bookingToDelete.get());
			
	}
}
