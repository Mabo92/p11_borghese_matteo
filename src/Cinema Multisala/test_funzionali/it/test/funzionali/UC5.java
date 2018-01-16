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

public class UC5 {
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
		r_1 = new Recensione(2, 2, "brutto...", "Mario");
		recensioni = new ArrayList<Recensione>();
		recensioni.add(r);
		recensioni.add(r_1);
	}

	//Scenario principale: consultare scheda film
	//avvenuto con successo.
	@Test
	public void testConsultareSchedaFilm_scenarioPrincipale() {
		String[] info;
		f.setRecensione(recensioni);
		info = f.ottieniInfoFilm();
		assertNotNull(info);
		assertEquals("titolo:Blade Runner", info[0]);
		assertEquals("genere:fatascienza", info[1]);
		assertEquals("trama:Deckard è costretto...", info[2]);
		assertEquals("voto medio:4.8", info[3]);
		assertEquals("prezzo:5.5", info[4]);
		assertEquals("data uscita:25-06-1982", info[5]);
		assertEquals("data rimozione:25-09-1982", info[6]);
		assertEquals("durata:01:57\n", info[7]);	
		assertEquals("username:Luigi", info[8]);
		assertEquals("commento:bello...", info[9]);
		assertEquals("voto:4\n", info[10]);
		assertEquals("username:Mario", info[11]);
		assertEquals("commento:brutto...", info[12]);
		assertEquals("voto:2\n", info[13]);
		f.printInfoFIlm();
	}

}
