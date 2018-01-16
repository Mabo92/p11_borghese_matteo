package it.test.strutturali;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.Film;
import it.cinema.multisala.Recensione;

public class FilmTest {

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

	@Test
	public void testFilm() {
		assertNotNull(f);
		assertEquals(1, f.getIdFilm());
		assertEquals("Blade Runner", f.getTitolo());
		assertEquals("fatascienza", f.getGenere());
		assertEquals("Deckard è costretto...", f.getTrama());
		assertEquals(4.8, f.getVotoMedio(), 0);
		assertEquals(durata, f.getDurata());
		assertEquals(dataUscita, f.getDataUscita());
		assertEquals(dataRimozione, f.getDataRimozione());
	}

	@Test
	public void testGetIdFilm() {
		f.setIdFilm(2);
		assertEquals(2, f.getIdFilm());
	}

	@Test
	public void testGetTitolo() {
		f.setTitolo("Il padrino");
		assertEquals("Il padrino", f.getTitolo());
	}

	@Test
	public void testGetGenere() {
		f.setGenere("drammatico");
		assertEquals("drammatico", f.getGenere());
	}

	@Test
	public void testGetTrama() {
		f.setTrama("New York, 1945. Vito...");
		assertEquals("New York, 1945. Vito...", f.getTrama());
	}

	@Test
	public void testGetVotoMedio() {
		f.setVotoMedio(4.9);
		assertEquals(4.9, f.getVotoMedio(), 0);
	}

	@Test
	public void testGetPrezzo() {
		f.setPrezzo(6.50);
		assertEquals(6.50, f.getPrezzo(), 0);
	}

	@Test
	public void testGetDurata() {
		durata_1 = LocalTime.of(2 , 05);
		f.setDurata(durata_1);
		assertEquals(durata_1, f.getDurata());
	}

	@Test
	public void testGetDataUscita() {
		dataUscita_1 = LocalDateTime.of(1972, Month.SEPTEMBER, 
										 21, 00, 00, 00);
		f.setDataUscita(dataUscita_1);
		assertEquals(dataUscita_1, f.getDataUscita());
	}


	@Test
	public void testOttieniInfoFilm() {
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
	}

	@Test
	public void testAggiungiRecensione() {
		Recensione r_2;
		Recensione r_3;
		int size = f.getRecensione().size();
		r_2 = new Recensione(3, 5, "bellissimo...", "Tony");
		r_3 = new Recensione(4, 0, "bellissimo...", "Tony");
		f.setVotoMedio(0);
		//recensione valida viene inserita tra quelle disponibili
		//viene aggionato anche il voto medio
		f.aggiungiRecensione(r_2);
		assertTrue(f.getRecensione().contains(r_2));
		assertEquals(size + 1, f.getRecensione().size());
		assertEquals(5, f.getVotoMedio(), 0);
		//recensione non valida non viene inserita tra le disponibili
		f.aggiungiRecensione(r_3);
		assertEquals(size + 1, f.getRecensione().size());
	}

	@Test
	public void testRimuoviRecensione() {
		f.setVotoMedio(0);
		f.setRecensione(recensioni);
		//rimuovo la recensione e ricalcolo voto medio
		assertTrue(f.rimuoviRecensione(r_1));
		assertFalse(f.getRecensione().contains(r_1));
		assertEquals(4, f.getVotoMedio(), 0);
		//impossibile eliminare una recensione non presente
		assertFalse(f.rimuoviRecensione(r_1));
	}

	@Test
	public void testPrintInfoFIlm() {
		f.setRecensione(recensioni);
		f.printInfoFIlm();
	}

}
