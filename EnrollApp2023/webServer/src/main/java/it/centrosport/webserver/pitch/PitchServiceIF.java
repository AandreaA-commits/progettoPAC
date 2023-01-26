package it.centrosport.webserver.pitch;

import java.util.List;

public interface PitchServiceIF {
	public Pitch getPitch(String id);

	public List<Pitch> getPitches();

	public Pitch createPitch(Pitch pitch);

	public void deletePitch(String id);
}
