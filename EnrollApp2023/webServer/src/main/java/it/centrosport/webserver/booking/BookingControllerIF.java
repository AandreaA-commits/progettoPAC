package it.centrosport.webserver.booking;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface BookingControllerIF {

	public Iterable<Booking> getBookings();
	
	public Booking getBooking(@PathVariable String idBooking);
	
	public void deleteBooking(@PathVariable("idBooking") String idBooking);
	
	public Booking createBooking(@RequestBody BookingDto bookingDto);
}
