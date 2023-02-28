package it.centrosport.webserver.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "bookings")
public class BookingController implements BookingControllerIF {

	private final BookingService bookingService;
	
	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	private Booking dtoToBooking(BookingDto bookingDto) {
		var booking = new Booking();
		booking.setIdCampoPrenotato(bookingDto.getIdCampoPrenotato());
		booking.setIdUtente(bookingDto.getIdUtente());
		booking.setDate(bookingDto.getDate());
		booking.setPostiPrenotati(bookingDto.getPostiPrenotati());
		
		return booking;
	}

	@GetMapping
	public Iterable<Booking> getBookings() {
		return bookingService.getBookings();
	}

	@GetMapping("{idBooking}")
	public Booking getBooking(String idBooking) {
		return bookingService.getBooking(idBooking);
	}

	@DeleteMapping("{idBooking}")
	public void deleteBooking(@PathVariable("idBooking") String idBooking) {
		bookingService.deleteBooking(idBooking);
	}

	@PostMapping
	public Booking createBooking(@RequestBody BookingDto bookingDto) {
		var booking = dtoToBooking(bookingDto);
		return bookingService.createBooking(booking);
	}
	
	
	
}
