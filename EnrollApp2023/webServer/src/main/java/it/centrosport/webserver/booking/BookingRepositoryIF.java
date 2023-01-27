package it.centrosport.webserver.booking;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepositoryIF extends MongoRepository<Booking, String> {
	public Optional<Booking> findByIdUtente(String idUtente);
	public Optional<Booking> findById(String id);
}
