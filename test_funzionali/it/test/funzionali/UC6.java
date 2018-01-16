package it.test.funzionali;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.Film;
import it.cinema.multisala.Recensione;

public class UC6 {
	Film f;
	Recensione r;
	Recensione r_1;
	LocalTime durata;
	LocalTime durata_1;
	LocalDateTime dataUscita;
	LocalDateTime dataUscita_1;
	LocalDateTime dataRimozione;
	LocalDateTime dataRimozione_1;
	ArrayList<Recensione> recensioni;

	@Before
	public void setUp() throws Exception {
		durata = LocalTime.of(1 , 57);
		dataUscita = LocalDateTime.of(1982, Month.JUNE, 
									   25, 00, 00, 00);
		dataRimozione = LocalDateTime.of(1982, Month.SEPTEMBER,
										  25, 00, 00, 00);
		
		f = new Film(1, "Blade Runner", "fatascienza", "Deckard è costretto...", 4.8, 
			5.50, durata, dataUscita, dataRimozione);
		r = new Recensione(1, 4, "bello...", "Luigi");
		r_1 = new Recensione(2, 3, "brutto...", "Mario");
		recensioni = new ArrayList<Recensione>();
		recensioni.add(r);
		recensioni.add(r_1);
		f.setRecensione(recensioni);
	}
	
	//Scenario principale: scrivere recensioni con successo
	@Test
	public void testScrivereRecensione_scenarioPrincipale() {
		int size = f.getRecensione().size();
		Recensione r_2 = new Recensione(3, 5, "bellissimo...", "Tony");
		f.aggiungiRecensione(r_2);
		assertTrue(f.getRecensione().contains(r_2));
		assertEquals(size + 1, f.getRecensione().size());
		assertEquals(4, f.getVotoMedio(), 0);
	}
	
	//Scenario alternativo: l utente non ha inserito il voto
	//ma il giudizio sintetico è valido (4A)
	@Test
	public void testScrivereRecensione_scenarioAlternativo_4A() {
		int size = f.getRecensione().size();
		Recensione r_2 = new Recensione(3, 0, "bellissimo...", "Tony");
		f.aggiungiRecensione(r_2);
		assertEquals(size, f.getRecensione().size());
	}
	
	//Scenario alternativo: l utente non ha inserito un giudizio
	//non valido, ma il voto in stelle correttamente (4B)
	@Test
	public void testScrivereRecensione_scenarioAlternativo_4B() {
		int size = f.getRecensione().size();
		Recensione r_2 = new Recensione(3, 4, "", "Tony");
		Recensione r_3 = new Recensione(3, 4, null, "Tony");
		f.aggiungiRecensione(r_2);
		assertEquals(size, f.getRecensione().size());
		f.aggiungiRecensione(r_3);
		assertEquals(size, f.getRecensione().size());
		//l utente ha inserito una o più parole non conformi (1B)
		Recensione r_4 = new Recensione(3, 2, "insulto, brutto", "Tony");
		String expectedCom = "****, brutto";
		f.aggiungiRecensione(r_4);
		assertTrue(f.getRecensione().contains(r_4));
		assertEquals(size + 1, f.getRecensione().size());
		assertEquals(3, f.getVotoMedio(), 0);
		assertEquals(expectedCom,f.getRecensione().get(2).getCommento());
	}

}
