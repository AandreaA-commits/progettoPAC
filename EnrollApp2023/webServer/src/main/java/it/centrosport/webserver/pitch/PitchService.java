package it.centrosport.webserver.pitch;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PitchService {
	//questa classe rappresenta il servizio vero e proprio ed implementa i metodi di PitchRepository
	
	
	private final PitchRepository pitchRepository;
	
	@Autowired
	public PitchService(PitchRepository pitchRepository) {
		this.pitchRepository = pitchRepository;
	}
	
	public Pitch getPitch(String id) {
		Optional<Pitch> pitch = pitchRepository.findById(id);
		if(!pitch.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		
		return pitch.get();
	}
	
	public List<Pitch> getPitches() {
		return pitchRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	
	public Pitch createPitch(Pitch pitch) {
		return pitchRepository.save(pitch);
	}
	
	public void deletePitch(String id) {
		Optional<Pitch> pitchToDelete = pitchRepository.findById(id);
		
		if(!pitchToDelete.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id non presente");
		
		pitchRepository.delete(pitchToDelete.get());
		
	}
	

	
}
