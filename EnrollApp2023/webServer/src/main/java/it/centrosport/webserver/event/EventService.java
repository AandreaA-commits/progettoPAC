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
		
		//Cancelliamo anche le prenotazioni inerenti all'evento
		for(int i=0;i<eventToDelete.get().getPlayers().size();i++) {
			deleteEventEnrollment(eventToDelete.get().getPlayers().get(i).getIdEnrollment());
		}
		
		eventRepository.delete(eventToDelete.get());
	}

	public EventEnrollment createEventEnrollment(EventEnrollment eventEnrollment) {
		Optional<Event> event = eventRepository.findById(eventEnrollment.getIdEvent());
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento non esistente");
		int nPlayers = 0;
		//Controllo massimo numero di iscritti
		ArrayList<EventEnrollment> p = event.get().getPlayers();
		for (EventEnrollment e : p) {
			nPlayers += e.getNumIscritti();
		}
		if(nPlayers >= event.get().getMaxPlayers()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Iscrizioni massime raggiunte");
		//Controllo  iscrizione di numero minore rispetto una squadra intera
		if(eventEnrollment.getNumIscritti() > MAX_TEAM) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numero massimo partecipanti " + MAX_TEAM + " raggiunto");
		eventEnrollmentRepository.save(eventEnrollment);
		event.get().addPlayers(eventEnrollment);
		//Salviamo la lista di iscrizioni in event con anche la nuova aggiunta
		eventRepository.save(event.get());
		return eventEnrollmentRepository.save(eventEnrollment);
	}
	
	public ArrayList<EventEnrollment> getPlayers(String id) {
		Optional<Event> event = eventRepository.findById(id);
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		
		return event.get().getPlayers();
	}
	
	public void deleteEventEnrollment(String id) {
		Optional<EventEnrollment> eventEnrollmentToDelete = eventEnrollmentRepository.findById(id);
		if(!eventEnrollmentToDelete.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID non presente");
		Optional<Event> event= eventRepository.findById(eventEnrollmentToDelete.get().getIdEvent());
		event.get().removePlayers(eventEnrollmentToDelete.get());
		eventRepository.save(event.get());
		eventEnrollmentRepository.delete(eventEnrollmentToDelete.get());
	}

	@Override
	public ArrayList<ArrayList<String>> getTeams(String idEvent) {
		
		//Array List di squadre(ArrayList di id utenti)
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
		
		//ArrayList di indici (inseriti in una squadra)
		ArrayList<Integer> idx = new ArrayList<Integer>();
		
		//Copia di supporto per la lista di iscrizioni
		ArrayList<EventEnrollment> help = new ArrayList<EventEnrollment>();
		
		Optional<Event> event = eventRepository.findById(idEvent);
		if(!event.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
				"Evento non esistente");
		
		//ArrayList di iscrizioni all'evento
		ArrayList<EventEnrollment> lista = event.get().getPlayers();
		
		//Somma totale dei partecipanti (numIscritti)
		int nPlayers = 0;
		for (EventEnrollment ee : lista) {
			nPlayers += ee.getNumIscritti();
		}
		if(nPlayers < MIN_PLAYERS) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
				"Almeno " + MIN_PLAYERS + " giocatori devono partecipare all'evento");
		
		//Ordiniamo l'ArrayList in ordine decrescente
		Collections.sort(lista, Collections.reverseOrder());
		
		help.addAll(lista);//Copia di lista in help
		
		// Tentativo di formare squadre di esattamente MAX_TEAMS 
		//(preferibile rispetto alla creazione di squadre flessibili)
		for(int i=0; i<help.size(); i++) {
			idx.add(i);
			int sum = help.get(i).getNumIscritti();
			
			//Caso in cui c'è un iscrizione di esattamente MAX_TEAM
			if(sum == MAX_TEAM) {
				result.add(idx);
			}
			
			//Provo ad accoppiare l'iscrizione con altre
			for(int j=i+1; j<help.size(); j++) {
				//Se formo una squadra perfetta, la confermo
				if(sum + help.get(j).getNumIscritti() == MAX_TEAM) {
					sum += help.get(j).getNumIscritti();
					idx.add(j);
					result.add(idx);
				}
				//Se la squadra è ancora inferiore a MAX_TEAM, aggiungo l'iscrizione e continuo
				else if(sum + help.get(j).getNumIscritti() < MAX_TEAM) {
					//par.add(help.get(j).getNumIscritti());
					sum += help.get(j).getNumIscritti();
					idx.add(j);
				}
			}
			//In help modifico il valore di numIscritti delle iscrizioni aggiunte ad un MAX_VALUE 
			//in modo che l'algoritmo non le utilizzi più. 
			//Non cancelliamo l'elemento in modo da mantenere gli indici
			if(sum == MAX_TEAM) {
				for(int j=0; j<idx.size(); j++) {
					help.get(idx.get(j)).setNumIscritti(MAX_VALUE);
					
				}
			}
			//Svuoto la lista idx
			idx = new ArrayList<>();
		}
		
		//Una volta create le squadre di numero perfetto MAX_TEAM, con i restanti si provano a 
		//creare squadre con dimensione flessibile (MAX_TEAM + FLEX)
		for(int i=0; i<help.size(); i++) {
			idx.add(i);
			int sum = help.get(i).getNumIscritti();
			
			//Cambia solo la condizione per l'aggiunta nelle squadre
			for(int j=i+1; j<help.size(); j++) {
				if(sum + help.get(j).getNumIscritti() <= MAX_TEAM + FLEX && sum + 
						help.get(j).getNumIscritti() > MAX_TEAM ) {
					sum += help.get(j).getNumIscritti();
					idx.add(j);
					result.add(idx);
				} else if(sum + help.get(j).getNumIscritti() < MAX_TEAM) {
					sum += help.get(j).getNumIscritti();
					idx.add(j);
				}
			}
			
			if(sum <= MAX_TEAM + FLEX && sum > MAX_TEAM) {
				for(int j=0; j<idx.size(); j++) {
					help.get(idx.get(j)).setNumIscritti(MAX_VALUE);
				}
			}
			
			idx = new ArrayList<>();
		}
		
		//Infine si prova ad aggiungere a squadre esistenti le ultime iscrizioni rimaste
		for(int i=0;i<help.size();i++) {
			//Escludo già i valori posti a MAX_VALUE in help
			if(help.get(i).getNumIscritti()<MAX_TEAM) {
				for(int j=0;j<result.size();j++) {
					int sumSquad = 0;
					for(int z=0;z<result.get(j).size();z++) {
						sumSquad+= lista.get(result.get(j).get(z)).getNumIscritti();
					}
					if(sumSquad + help.get(i).getNumIscritti()<MAX_TEAM + FLEX) {
						result.get(j).add(i);
						help.get(i).setNumIscritti(MAX_VALUE);
					}
				}
			}
		}
		//Indici delle iscrizioni non inserite in squadre
		ArrayList<Integer> noTeam = new ArrayList<Integer>();
		
		for(int i=0;i<help.size();i++) {
			if(help.get(i).getNumIscritti()!=MAX_VALUE) {
				noTeam.add(i);
			}
		}
		
		return getEventTeams(result, lista, noTeam);
		
	
	}
	
	//Si da in input la divisione in squadre sottoforma di indici e la lista di iscrizioni
	//e restituisce la divisione in squadre sottoforma di codici id utente delle iscrizioni
	private ArrayList<ArrayList<String>> getEventTeams(ArrayList<ArrayList<Integer>> p, 
			ArrayList<EventEnrollment> l, ArrayList<Integer> noTeam){
		ArrayList<ArrayList<String>> squad = new ArrayList<>();
		for(int i =0; i<p.size(); i++) {
			ArrayList<String> squadra = new ArrayList<String>();
			for(int j=0; j<p.get(i).size(); j++) {
				squadra.add(l.get(p.get(i).get(j)).getIdUtenteIscrizione());
			}
			squad.add(squadra);
		}
		//Creazione di un array di Id utenti rifiutati
		ArrayList<String> idNoTeam = new ArrayList<String>();
		idNoTeam.add("Non accettati:");
		
		for(int i=0;i<noTeam.size();i++) {
			idNoTeam.add(l.get(noTeam.get(i)).getIdUtenteIscrizione());
		}
		//Aggiungo in ultima posizione dell'array di risposta l'array di utenti non accettati
		squad.add(idNoTeam);
		return squad;
	}
}
