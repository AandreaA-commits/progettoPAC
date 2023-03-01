import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	private final static int MAX_VALUE = 100;
	private static final int MAX_TEAM = 5;
	private static final int FLEX = 2;

	public static ArrayList<ArrayList<Integer>> Alg(ArrayList<Integer> lista){
		
		//Array List di squadre(ArrayList di id utenti)
				ArrayList<ArrayList<Integer>> fin = new ArrayList<ArrayList<Integer>>(); 
				
				//ArrayList di indici (inseriti in una squadra)
				ArrayList<Integer> idx = new ArrayList<Integer>();
				
				//ArrayList di interi (numero iscritti)
				ArrayList<Integer> par = new ArrayList<Integer>();
				
				//Copia di supporto per la lista di iscrizioni
				ArrayList<Integer> help = new ArrayList<Integer>();
				
				//Ordiniamo l'ArrayList in ordine decrescente
				Collections.sort(lista, Collections.reverseOrder());
				
				help.addAll(lista);//Copia di lista in help
				
				// Tentativo di formare squadre di esattamente MAX_TEAMS 
				//(preferibile rispetto alla creazione di squadre flessibili)
				for(int i=0; i<help.size(); i++) {
					idx.add(i);
					par.add(help.get(i));
					int sum = help.get(i);
					
					//Caso in cui c'è un iscrizione di esattamente MAX_TEAM
					if(sum == MAX_TEAM) {
						fin.add(idx);
					}
					
					//Provo ad accoppiare l'iscrizione con altre
					for(int j=i+1; j<help.size(); j++) {
						//Se formo una squadra perfetta, la confermo
						if(sum + help.get(j) == MAX_TEAM) {
							sum += help.get(j);
							par.add(help.get(j));
							idx.add(j);
							fin.add(idx);
						}
						//Se la squadra è ancora inferiore a MAX_TEAM, aggiungo l'iscrizione e continuo
						else if(sum + help.get(j) < MAX_TEAM) {
							par.add(help.get(j));
							sum += help.get(j);
							idx.add(j);
						}
					}
					//In help modifico il valore di numIscritti delle iscrizioni aggiunte ad un MAX_VALUE 
					//in modo che l'algoritmo non le utilizzi più. 
					//Non cancelliamo l'elemento in modo da mantenere gli indici
					if(sum == MAX_TEAM) {
						for(int j=0; j<idx.size(); j++) {
							help.set(idx.get(j),MAX_VALUE);
							
						}
					}
					//Svuoto le liste idx e par
					idx = new ArrayList<>();
					par = new ArrayList<>();
				}
				
				//Una volta create le squadre di numero perfetto MAX_TEAM, con i restanti si provano a 
				//creare squadre con dimensione flessibile (MAX_TEAM + FLEX)
				for(int i=0; i<help.size(); i++) {
					idx.add(i);
					par.add(help.get(i));
					int sum = help.get(i);
					
					
					//Cambia solo la condizione per l'aggiunta nelle squadre
					for(int j=i+1; j<help.size(); j++) {
						if(sum + help.get(j) <= MAX_TEAM + FLEX && sum + 
								help.get(j) > MAX_TEAM ) {
							sum += help.get(j);
							par.add(help.get(j));
							idx.add(j);
							fin.add(idx);
						} else if(sum + help.get(j) < MAX_TEAM) {
							par.add(help.get(j));
							sum += help.get(j);
							idx.add(j);
						}
					}
					
					if(sum <= MAX_TEAM + FLEX && sum > MAX_TEAM) {
						for(int j=0; j<idx.size(); j++) {
							help.set(idx.get(j),MAX_VALUE);
						}
					}
					
					idx = new ArrayList<>();
					par = new ArrayList<>();
				}
				
				//Infine si prova ad aggiungere a squadre esistenti le ultime iscrizioni rimaste
				for(int i=0;i<help.size();i++) {
					//Escludo già i valori posti a MAX_VALUE in help
					if(help.get(i)<MAX_TEAM) {
						for(int j=0;j<fin.size();j++) {
							int sumSquad = 0;
							for(int z=0;z<fin.get(j).size();z++) {
								sumSquad+= lista.get(fin.get(j).get(z));
							}
							if(sumSquad + help.get(i)<MAX_TEAM + FLEX) {
								fin.get(j).add(i);
								help.set(i,MAX_VALUE);
							}
						}
					}
				}
				//Indici delle iscrizioni non inserite in squadre
				ArrayList<Integer> noTeam = new ArrayList<Integer>();
				
				for(int i=0;i<help.size();i++) {
					if(help.get(i)!=MAX_VALUE) {
						noTeam.add(i);
					}
				}
				fin.add(noTeam);
				return fin;
		
	}
}
