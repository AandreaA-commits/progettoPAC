package it.centrosport.webserver.event;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventEnrollmentRepositoryIF extends MongoRepository<EventEnrollment, String>{
	public Optional<EventEnrollment> findById(String id);
}
