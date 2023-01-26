package it.centrosport.webserver.pitch;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PitchControllerIF {
	public Iterable<Pitch> getPitches();
	public Pitch getPitch(@PathVariable String pitchId);
	public void deletePitch(@PathVariable("pitchId") String pitchId);
	public Pitch createPitch(@RequestBody PitchDto pitchDto);
}
