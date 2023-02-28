package it.centrosport.webserver.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService implements EventServiceIF {

	private final static int MAX_TEAM = 5;
	private final static int FLEX = 2;
	private final static int MAX_VALUE = 100;
	private final static int MIN_PLAYERS = 10;
	private final EventRepositoryIF eventRepository;
	private final EventEnrollmentRepositoryIF eventEnrollmentRepository;
	
	@Autowired
	public EventService(EventRepositoryIF eventRepository, EventEnrollmentRepositoryIF eventEnrollmentRepository) {
		this.eventRepository = eventRepository;
		this.eventEnrollmentRepository = eventEnrollmentRepository;
	}
	
	public Event getEvent(String id) {
		Optional<Event> event = eventRepository.findById(id);
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		
		return event.get();
	}
	
	public List<Event> getEvents() {
		return eventRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Event createEvent(Event event) {
		return eventRepository.save(event);
	}
	
	public void deleteEvent(String id) {
		Optional<Event> eventToDelete = eventRepository.findById(id);
		if(!eventToDelete.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID non presente");
		eventRepository.delete(eventToDelete.get());
	}

	public EventEnrollment createEventEnrollment(EventEnrollment eventEnrollment) {
		Optional<Event> event = eventRepository.findById(eventEnrollment.getIdEvent());
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento non esistente");
		int nPlayers = 0;
		ArrayList<EventEnrollment> p = event.get().getPlayers();
		for (EventEnrollment e : p) {
			nPlayers += e.getNumIscritti();
		}
		if(nPlayers >= event.get().getMaxPlayers()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Iscrizioni massime raggiunte");
		if(eventEnrollment.getNumIscritti() > MAX_TEAM) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numero massimo partecipanti " + MAX_TEAM + " raggiunto");
		event.get().addPlayers(eventEnrollment);
		eventRepository.save(event.get());
		return eventEnrollmentRepository.save(eventEnrollment);
	}
	
	public ArrayList<EventEnrollment> getPlayers(String id) {
		Optional<Event> event = eventRepository.findById(id);
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		
		return event.get().getPlayers();
	}

	@Override
	public ArrayList<ArrayList<String>> getTeams(String idEvent) {
		
		ArrayList<ArrayList<Integer>> fin = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> idx = new ArrayList<Integer>();
		ArrayList<Integer> par = new ArrayList<Integer>();
		ArrayList<EventEnrollment> help = new ArrayList<EventEnrollment>();
		
		Optional<Event> event = eventRepository.findById(idEvent);
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento non esistente");
		
		
		ArrayList<EventEnrollment> lista = event.get().getPlayers();
		
		int nPlayers = 0;
		for (EventEnrollment ee : lista) {
			nPlayers += ee.getNumIscritti();
		}
		if(nPlayers < MIN_PLAYERS) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Almeno " + MIN_PLAYERS + " giocatori devono partecipare all'evento");
		
		Collections.sort(lista, Collections.reverseOrder());
		
		help.addAll(lista);
		
		
		for(int i=0; i<help.size(); i++) {
			idx.add(i);
			par.add(help.get(i).getNumIscritti());
			int sum = help.get(i).getNumIscritti();
			
			if(sum == MAX_TEAM) {
				fin.add(idx);
			}
			
			for(int j=i+1; j<help.size(); j++) {
				if(sum + help.get(j).getNumIscritti() == MAX_TEAM) {
					sum += help.get(j).getNumIscritti();
					par.add(help.get(j).getNumIscritti());
					idx.add(j);
					fin.add(idx);
				} else if(sum + help.get(j).getNumIscritti() < MAX_TEAM) {
					par.add(help.get(j).getNumIscritti());
					sum += help.get(j).getNumIscritti();
					idx.add(j);
				}
			}
			
			if(sum == MAX_TEAM) {
				for(int j=0; j<idx.size(); j++) {
					help.get(idx.get(j)).setNumIscritti(MAX_VALUE);
					
				}
			}
			
			idx = new ArrayList<>();
			par = new ArrayList<>();
		}
		
		//parte flex	
		for(int i=0; i<help.size(); i++) {
			idx.add(i);
			par.add(help.get(i).getNumIscritti());
			int sum = help.get(i).getNumIscritti();
			
			if(sum == MAX_TEAM) {
				fin.add(idx);
			}
			
			for(int j=i+1; j<help.size(); j++) {
				if(sum + help.get(j).getNumIscritti() <= MAX_TEAM + FLEX && sum + help.get(j).getNumIscritti() > MAX_TEAM ) {
					sum += help.get(j).getNumIscritti();
					par.add(help.get(j).getNumIscritti());
					idx.add(j);
					fin.add(idx);
				} else if(sum + help.get(j).getNumIscritti() < MAX_TEAM) {
					par.add(help.get(j).getNumIscritti());
					sum += help.get(j).getNumIscritti();
					idx.add(j);
				}
			}
			
			if(sum == MAX_TEAM) {
				for(int j=0; j<idx.size(); j++) {
					help.get(idx.get(j)).setNumIscritti(MAX_VALUE);
				}
			}
			
			idx = new ArrayList<>();
			par = new ArrayList<>();
		}
		
		return getEventTeams(fin, lista);
		
	
	}
	
	private ArrayList<ArrayList<String>> getEventTeams(ArrayList<ArrayList<Integer>> p, ArrayList<EventEnrollment> l){
		ArrayList<ArrayList<String>> squad = new ArrayList<>();
		for(int i =0; i<p.size(); i++) {
			ArrayList<String> squadra = new ArrayList<String>();
			for(int j=0; j<p.get(i).size(); j++) {
				squadra.add(l.get(p.get(i).get(j)).getIdUtenteIscrizione());
			}
			squad.add(squadra);
		}
		return squad;
	}
}
