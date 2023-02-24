import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lista.add(5);
		lista.add(4);
		lista.add(4);
		lista.add(4);
		lista.add(3);
		lista.add(3);
		lista.add(3);
		lista.add(2);
		lista.add(2);
		lista.add(1);

		System.out.println(Main.Algoritmo(lista, 5, 2));
	}

	public static ArrayList<Integer> Algoritmo(ArrayList<Integer> p, int dim, int flex){
		
		ArrayList<Integer> sol = new ArrayList<>();
		ArrayList<Integer> parz = new ArrayList<>();
		
		//eventuale sort decrescente
		boolean flag = true;
		while(flag && p.size()!=0){
			flag = false;
			if(p.get(0)==dim){
				sol.add(p.remove(0));
				flag = true;
			}
		}
		
		//siamo nel caso in cui non arriviamo alla dimensione della squadra
		for(int i=0; i<p.size()-1; i++){
			for(int j=i+1; j<p.size(); j++){
				if(p.get(i) + p.get(j) == dim){
					sol.add(p.get(i) + p.get(j));
				}
				if(p.get(i) + p.get(j) < dim){
					
				}
									
		}
		
		//siamo nel caso in cui le squadre rimanenti non possono formare una squadra di dimensione esatta
		while(p.size()>1){
			int t = p.remove(0);
			if(t + p.get(p.size()-1)<dim+flex){
				sol.add(t + p.remove(p.size()-1));
			}
		}
		
		//qua ci rimane al massimo una prenotazione <= dim-1... gli comunichiamo che ci dispiace		
		}
		
		return sol;
		
	}

}
