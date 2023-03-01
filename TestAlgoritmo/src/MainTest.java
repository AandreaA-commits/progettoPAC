import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testAlgoritmo() {
		Main m = new Main();
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> risposta = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> squadra = new ArrayList<Integer>();
		ArrayList<Integer> rimanenti = new ArrayList<Integer>();
		
		//Test 1---------------------------------------------------------------------------------
		//Input:
		//Iscrizione 0: 5
		lista.add(5);
		
		//Output:
		//Squadra 0: 0
		//Rimanenti: \
		squadra.add(0);
		risposta.add(squadra);
		risposta.add(rimanenti);
		
		assertEquals(risposta,Main.Alg(lista));
		lista = new ArrayList<Integer>();
		risposta = new ArrayList<ArrayList<Integer>>();
		squadra = new ArrayList<Integer>();
		rimanenti = new ArrayList<Integer>();
		
		//Test 2--------------------------------------------------------------------------------
		//Input:
		//Iscrizione 0: 4
		//Iscrizione 1: 1
		lista.add(4);
		lista.add(1);
		
		//Output:
		//Squadra 0: 0,1
		//Rimanenti: \
		squadra.add(0);
		squadra.add(1);
		risposta.add(squadra);
		risposta.add(rimanenti);
		
		assertEquals(risposta,Main.Alg(lista));
		lista = new ArrayList<Integer>();
		risposta = new ArrayList<ArrayList<Integer>>();
		squadra = new ArrayList<Integer>();
		rimanenti = new ArrayList<Integer>();
		
		//Test 3------------------------------------------------------------------------------------
		//Input:
		//Iscrizione 0: 2
		//Iscrizione 1: 2
		//Iscrizione 2: 1
		lista.add(2);
		lista.add(2);
		lista.add(1);
		
		//Output
		//Squadra 0: 0,1,2
		//Rimanenti: \
		squadra.add(0);
		squadra.add(1);
		squadra.add(2);
		risposta.add(squadra);
		risposta.add(rimanenti);
		
		assertEquals(risposta,Main.Alg(lista));
		lista = new ArrayList<Integer>();
		risposta = new ArrayList<ArrayList<Integer>>();
		squadra = new ArrayList<Integer>();
		rimanenti = new ArrayList<Integer>();
		
		//Test 4------------------------------------------------------------------------------------
		//Input:
		//Iscrizione 0: 2
		//Iscrizione 1: 2
		//Iscrizione 2: 2
		lista.add(2);
		lista.add(2);
		lista.add(2);
		
		//Output
		//Squadra 0: 0,1,2 (creazione squadra flex)
		//Rimanenti: \
		squadra.add(0);
		squadra.add(1);
		squadra.add(2);
		risposta.add(squadra);
		risposta.add(rimanenti);
		
		assertEquals(risposta,Main.Alg(lista));
		lista = new ArrayList<Integer>();
		risposta = new ArrayList<ArrayList<Integer>>();
		squadra = new ArrayList<Integer>();
		rimanenti = new ArrayList<Integer>();

		//Test 5------------------------------------------------------------------------------------
		//Input:
		//Iscrizione 0: 3
		//Iscrizione 1: 2
		//Iscrizione 2: 1
		lista.add(3);
		lista.add(2);
		lista.add(1);
		
		//Output
		//Squadra 0: 0,1,2 (modifica squadra esistente flex)
		//Rimanenti: \
		squadra.add(0);
		squadra.add(1);
		squadra.add(2);
		risposta.add(squadra);
		risposta.add(rimanenti);
		
		assertEquals(risposta,Main.Alg(lista));
		lista = new ArrayList<Integer>();
		risposta = new ArrayList<ArrayList<Integer>>();
		squadra = new ArrayList<Integer>();
		rimanenti = new ArrayList<Integer>();
		
		//Test 5------------------------------------------------------------------------------------
		//Input:
		//Iscrizione 0: 3
		//Iscrizione 1: 3
		//Iscrizione 2: 2
		lista.add(3);
		lista.add(3);
		lista.add(2);
		
		
		//Output
		//Squadra 0: 0,2 (squadra di numero perfetto preferita)
		//Rimanenti: 2
		squadra.add(0);
		squadra.add(2);
		rimanenti.add(1);
		risposta.add(squadra);
		risposta.add(rimanenti);
		
		assertEquals(risposta,Main.Alg(lista));
		lista = new ArrayList<Integer>();
		risposta = new ArrayList<ArrayList<Integer>>();
		squadra = new ArrayList<Integer>();
		rimanenti = new ArrayList<Integer>();
	}

}
