package it.test.strutturali;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.Cinema;
import it.cinema.multisala.Film;
import it.cinema.multisala.Prenotazione;
import it.cinema.multisala.Programmazione;
import it.cinema.multisala.Recensione;
import it.cinema.multisala.Sala;

public class CinemaTest {
	
	Cinema c;
	Programmazione prog;
	Programmazione prog_1;
	ArrayList<Programmazione> programmazioni;
	Prenotazione p;
	Prenotazione p_1;
	int filaPosti[];
	int colonnaPosti[];
	ArrayList<Prenotazione> prenotazioni;
	ArrayList<Prenotazione> prenotazioni_1;
	Film f;
	Film f_1;
	Sala salaProg;
	Sala salaProg_1;
	ArrayList<Sala> sala;
	LocalDateTime giorno;
	LocalDateTime giorno_1;
	LocalTime oraInizio;
	LocalTime oraInizio_1;
	LocalTime oraFine;
	LocalTime oraFine_1;

	@Before
	public void setUp() throws Exception {
		filaPosti = new int[] {1, 1};
		colonnaPosti = new int[] {1, 2};		
		LocalTime durata;
		LocalDateTime dataUscita;
		LocalDateTime dataRimozione;
		Recensione r;
		Recensione r_1;
		ArrayList<Recensione> recensioni;
		durata = LocalTime.of(1 , 57);
		oraInizio = LocalTime.of(12, 30);
		oraFine = oraInizio.plusHours(durata.getHour());
		oraFine = oraFine.plusMinutes(durata.getMinute());
		oraInizio_1 = LocalTime.of(10, 35);
		oraFine_1 = oraInizio_1.plusHours(durata.getHour());
		oraFine_1 = oraFine.plusMinutes(durata.getMinute());
		dataUscita = LocalDateTime.of(1982, Month.JUNE, 
									   25, 00, 00, 00);
		dataRimozione = LocalDateTime.of(1982, Month.SEPTEMBER,
										  25, 00, 00, 00);
		giorno = LocalDateTime.of(1992, Month.SEPTEMBER,
				  25, 00, 00, 00);
		giorno_1 = LocalDateTime.of(1992, Month.SEPTEMBER,
				  28, 00, 00, 00);
		c = new Cinema(1, "Fiumara", "Genova", "Via...");
		f = new Film(1, "Blade Runner", "fatascienza", "Deckard è costretto...", 4.8, 
			5.50, durata, dataUscita, dataRimozione);
		f_1 = new Film(2, "Il padrino", "drammatico", "new york...", 4.9, 
				5.50, durata, dataUscita, dataRimozione);
		r = new Recensione(1, 4, "bello...", "Luigi");
		r_1 = new Recensione(2, 2, "brutto...", "Mario");
		recensioni = new ArrayList<Recensione>();
		recensioni.add(r);
		recensioni.add(r_1);
		f.setRecensione(recensioni);
		salaProg = new Sala(1, "A", 2, 3);
		salaProg_1 = new Sala(2, "B", 3, 3);
		prog = new Programmazione(1, giorno, oraInizio, oraFine,
						 salaProg, f);
		prog_1 = new Programmazione(2, giorno_1, oraInizio_1, oraFine_1,
				 salaProg_1, f_1);
		p = new Prenotazione(1, 2, 5.50, prog);
		p.setColonnaPosti(colonnaPosti);
		p.setFilaPosti(filaPosti);
		p_1 = new Prenotazione(1, 2, 5.50, prog_1);
		p_1.setColonnaPosti(colonnaPosti);
		p_1.setFilaPosti(filaPosti);
		prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(p);
		prog.setPrenotazioni(prenotazioni);
		prenotazioni_1 = new ArrayList<Prenotazione>();
		prenotazioni_1.add(p_1);
		prog_1.setPrenotazioni(prenotazioni_1);
		programmazioni = new ArrayList<Programmazione>();
		programmazioni.add(prog);
		programmazioni.add(prog_1);
		c.setProgrammazione(programmazioni);
		sala = new ArrayList<Sala>();
		sala.add(salaProg);
		sala.add(salaProg_1);
		c.setSala(sala);
	}

	@Test
	public void testCinema() {
		assertNotNull(c);
		assertEquals(1, c.getIdCinema());
		assertEquals("Fiumara", c.getNome());
		assertEquals("Genova", c.getCitta());
		assertEquals("Via...", c.getIndirizzo());
		assertEquals(programmazioni, c.getProgrammazione());
		assertEquals(sala,c.getSala());
	}

	@Test
	public void testGetIdCinema() {
		c.setIdCinema(2);
		assertEquals(2, c.getIdCinema());
	}

	@Test
	public void testGetNome() {
		c.setNome("a");
		assertEquals("a", c.getNome());
	}

	@Test
	public void testGetCitta() {
		c.setCitta("Milano");
		assertEquals("Milano", c.getCitta());
	}

	@Test
	public void testGetIndirizzo() {
		c.setIndirizzo("h");
		assertEquals("h", c.getIndirizzo());
	}

	@Test
	public void testAggiungiSala() {
		//sala già presente
		assertFalse(c.aggiungiSala(salaProg));
		//sala inserita correttamente
		Sala s = new Sala(3, "c", 3, 3);
		assertTrue(c.aggiungiSala(s));
		//verifico l'inserimento
		assertTrue(c.getSala().contains(s));
	}

	@Test
	public void testRimuoviSala() {
		//sala non esistente nel cinema
		assertFalse(c.rimuoviSala(123));
		//rimozione corretta sala
		assertTrue(c.rimuoviSala(salaProg.getIdSala()));
		//verifico la rimozione
		assertFalse(c.getSala().contains(salaProg));
	}

	@Test
	public void testAggiungiProgrammazioneFilm() {
		LocalDateTime giorno;
		LocalTime oraInizio;
		LocalTime oraFine;
		Sala salaProg;
		LocalTime durata = LocalTime.of(1 , 57);
		giorno = LocalDateTime.of(1992, Month.SEPTEMBER,
				  25, 00, 00, 00);
		oraInizio = LocalTime.of(12, 30);
		oraFine = oraInizio.plusHours(durata.getHour());
		oraFine = oraFine.plusMinutes(durata.getMinute());
		salaProg = new Sala(3, "A", 2, 3);
		Programmazione p = new Programmazione(1, giorno, oraInizio, oraFine,
				 salaProg, f);
		int size = c.getProgrammazione().size();
		//provo ad inserire una programmazione gia esistente -> fallisce
		assertFalse(c.aggiungiProgrammazioneFilm(prog));
		assertEquals(size, c.getProgrammazione().size());
		/*inserisco una programmazione non esistente ma che genera 
		sovrapposizioni -> false. */
		p.setIdProgrammazione(4);
		p.getSalaProgrammazione().setIdSala(1);
		assertFalse(c.aggiungiProgrammazioneFilm(p));
		assertEquals(size, c.getProgrammazione().size());
		/*inserisco una programmazione non esistente e non genera 
		sovrapposizioni. */
		p.getSalaProgrammazione().setIdSala(3);
		assertTrue(c.aggiungiProgrammazioneFilm(p));
		assertTrue(c.getProgrammazione().contains(p));
	}

	@Test
	public void testRimuoviProgrammazioneFilm() {
		//rimuove programmazione esistente
		assertTrue(c.rimuoviProgrammazioneFilm(prog.getIdProgrammazione()));
		assertFalse(c.getProgrammazione().contains(prog));
		//la programmazione esiste ma la data di rimovione del film è 
		// successiva a quella attuale, impossibile rimuovere la prog.
		LocalDateTime dataRimozione = LocalDateTime.of(2222, Month.SEPTEMBER,
				  25, 00, 00, 00);
		prog_1.getFilm().setDataRimozione(dataRimozione);
		assertFalse(c.rimuoviProgrammazioneFilm(prog_1.getIdProgrammazione()));
		assertTrue(c.getProgrammazione().contains(prog_1));
		//fallisce se la programmazione non esiste
		//rimuovo quella che ho gia rimosso
		assertFalse(c.rimuoviProgrammazioneFilm(prog.getIdProgrammazione()));
	}
	
	@Test
	public void testModificaProgrammazioneFilm() {
		LocalDateTime giorno;
		LocalTime oraInizio;
		LocalTime oraFine;
		Sala salaProg;
		LocalTime durata = LocalTime.of(1 , 57);
		giorno = LocalDateTime.of(1992, Month.SEPTEMBER,
				  25, 00, 00, 00);
		oraInizio = LocalTime.of(12, 30);
		oraFine = oraInizio.plusHours(durata.getHour());
		oraFine = oraFine.plusMinutes(durata.getMinute());
		salaProg = new Sala(3, "A", 2, 3);
		Programmazione progAggiornata = new Programmazione(4, giorno, oraInizio, oraFine,
				 salaProg, f);
		Programmazione prog = new Programmazione(10, giorno, oraInizio, oraFine,
				 salaProg, f);
		//fallisco rimozione della precedente (programmazione inesistente)
		assertFalse(c.modificaProgrammazioneFilm(prog, progAggiornata));
		assertTrue(c.getProgrammazione().contains(prog_1));
		/*fallisco inserimento della nuova causa 
		 * sovrapposizioni con programmazioni già esistenti */
		progAggiornata.getSalaProgrammazione().setIdSala(1);
		assertFalse(c.modificaProgrammazioneFilm(prog_1, progAggiornata));
		assertTrue(c.getProgrammazione().contains(prog_1));
		progAggiornata.getSalaProgrammazione().setIdSala(3);
		//modifco la programmazione correttamente
		assertTrue(c.modificaProgrammazioneFilm(prog_1, progAggiornata));
		assertTrue(c.getProgrammazione().contains(progAggiornata));
		assertFalse(c.getProgrammazione().contains(prog_1));
	}

	@Test
	public void testControlloSovrapposizioni() {
		LocalDateTime giorno;
		LocalTime oraInizio;
		LocalTime oraFine;
		Sala salaProg;
		LocalTime durata = LocalTime.of(1 , 57);
		giorno = LocalDateTime.of(1992, Month.SEPTEMBER,
				  25, 00, 00, 00);
		oraInizio = LocalTime.of(12, 30);
		oraFine = oraInizio.plusHours(durata.getHour());
		oraFine = oraFine.plusMinutes(durata.getMinute());
		salaProg = new Sala(1, "A", 2, 3);
		/*programmazione con uguale giorno, ora inizio, fine, sala di una gia
		 * esistente si sovrappone.
		 */
		Programmazione p = new Programmazione(1, giorno, oraInizio, oraFine,
				 salaProg, f);
		assertTrue(c.controlloSovrapposizioni(p));
		/*programmazione con uguale giorno, ora inizio, fine di una gia esistente
		 * , ma sala diversa non si sovrappone.
		 */
		p.getSalaProgrammazione().setIdSala(3);
		assertFalse(c.controlloSovrapposizioni(p));
		/*programmazione con uguale sala, ora inizio, fine di una gia esistente
		 * , ma giorno diverso non si sovrappone.
		 */
		p.getSalaProgrammazione().setIdSala(1);
		LocalDateTime g = LocalDateTime.of(1992, Month.SEPTEMBER,
				  27, 00, 00, 00);
		p.setGiorno(g);
		assertFalse(c.controlloSovrapposizioni(p));
		/*programmazione con uguale sala e giorno di una gia esistente
		 * , ma orari diversi, non si sovrappone.
		 */
		p.setGiorno(giorno);
		oraInizio = LocalTime.of(18, 00);
		oraFine = LocalTime.of(19, 00);
		p.setOraInizio(oraInizio);
		p.setOraFine(oraFine);
		assertFalse(c.controlloSovrapposizioni(p));
		oraInizio = LocalTime.of(10, 00);
		oraFine = LocalTime.of(12, 00);
		p.setOraInizio(oraInizio);
		p.setOraFine(oraFine);
		assertFalse(c.controlloSovrapposizioni(p));
	}
}
