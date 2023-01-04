package it.centrosport.webserver.pitch;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "pitches")
public class PitchController {
	private final PitchService pitchService;
	
    @Autowired
	PitchController(PitchService pitchService){
		this.pitchService = pitchService;
	}
    
    @GetMapping
    public Iterable<Pitch> getPitches(){
        return pitchService.getPitches();
    }
    
    
    
    @GetMapping("{pitchId}")
    public Pitch getPitch(@PathVariable String pitchId) {
    	return pitchService.getPitch(pitchId);
    }
    
    @DeleteMapping("{pitchId}")
    public void deletePitch(@PathVariable("pitchId") String pitchId) {
        pitchService.deletePitch(pitchId);
    }
    
    @PostMapping
    public Pitch createPitch() {
        Pitch pitch = new Pitch("Milano", "Campo1");
    	return pitchService.createPitch(pitch);
    }
}
