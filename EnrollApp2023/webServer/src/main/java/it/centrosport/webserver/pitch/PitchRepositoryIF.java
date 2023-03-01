package it.centrosport.webserver.pitch;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PitchRepositoryIF extends MongoRepository<Pitch, String>{
	
	public Optional<Pitch> findById(String id);
}
