package it.centrosport.webserver.pitch;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PitchRepositoryIF extends MongoRepository<Pitch, String>{
	
	public Pitch findByNameAndLocation(String name, String location);
	public List<Pitch> findByLocation(String location);
	public Optional<Pitch> findById(String id);
}
