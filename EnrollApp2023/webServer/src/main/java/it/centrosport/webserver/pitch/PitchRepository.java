package it.centrosport.webserver.pitch;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PitchRepository extends MongoRepository<Pitch, String>{
	
	//Aggiungere qui sotto partiolari query sul database
	
	//L'interfaccia eredita da MongoRepository anche le principali operazioni (CRUD)
	public Pitch findByNameAndLocation(String name, String location);
	public List<Pitch> findByLocation(String location);
	public Optional<Pitch> findById(String id);
}
