package it.centrosport.webserver.booking;

import java.util.List;

public interface BookingServiceIF {
	
	public Booking getBooking(String id);
	
	public List<Booking> getBookings();
	
	public Booking createBooking(Booking booking);
	
	public void deleteBooking(String id);
}
