package it.centrosport.webserver.event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface EventRepositoryIF extends MongoRepository<Event, String>{

	public Event findByNameAndLocation(String name, String location);
	public List<Event> findByLocation(String location);
	public Optional<Event> findById(String id);
}
