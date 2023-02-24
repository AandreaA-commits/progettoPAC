package it.centrosport.webserver.booking;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepositoryIF extends MongoRepository<Booking, String> {
	
	//public Optional<Booking> findByIdUtente(String idUtente);
	public Optional<Booking> findByidCampoPrenotatoAndDataPrenotazione (String idCampo,LocalDateTime data);
	public Optional<Booking> findById(String id);
}
