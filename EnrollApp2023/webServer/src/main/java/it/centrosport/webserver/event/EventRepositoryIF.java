package it.centrosport.webserver.event;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface EventRepositoryIF extends MongoRepository<Event, String>{

	public Optional<Event> findById(String id);
}
