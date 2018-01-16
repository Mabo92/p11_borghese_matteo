package it.test.strutturali;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.Film;
import it.cinema.multisala.Prenotazione;
import it.cinema.multisala.Programmazione;
import it.cinema.multisala.Recensione;
import it.cinema.multisala.Sala;

public class ProgrammazioneTest {
	
	Programmazione prog;
	Prenotazione p;
	int filaPosti[];
	int colonnaPosti[];
	ArrayList<Prenotazione> prenotazioni;
	Film f;
	Film f_1;
	Sala salaProg;
	Sala salaProg_1;
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
		dataUscita = LocalDateTime.of(1982, Month.JUNE, 
									   25, 00, 00, 00);
		dataRimozione = LocalDateTime.of(1982, Month.SEPTEMBER,
										  25, 00, 00, 00);
		giorno = LocalDateTime.of(1992, Month.SEPTEMBER,
				  25, 00, 00, 00);
		f = new Film(1, "Blade Runner", "fatascienza", "Deckard è costretto...", 4.8, 
			5.50, durata, dataUscita, dataRimozione);
		r = new Recensione(1, 4, "bello...", "Luigi");
		r_1 = new Recensione(2, 2, "brutto...", "Mario");
		recensioni = new ArrayList<Recensione>();
		recensioni.add(r);
		recensioni.add(r_1);
		f.setRecensione(recensioni);
		salaProg = new Sala(1, "A", 2, 3);
		prog = new Programmazione(1, giorno, oraInizio, oraFine,
						 salaProg, f);
		p = new Prenotazione(1, 2, 5.50, prog);
		p.setColonnaPosti(colonnaPosti);
		p.setFilaPosti(filaPosti);
		prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(p);
	}

	@Test
	public void testProgrammazione() {
		assertNotNull(prog);
		assertEquals(1,prog.getIdProgrammazione());
		assertEquals(giorno, prog.getGiorno());
		assertEquals(oraInizio, prog.getOraInizio());
		assertEquals(oraFine, prog.getOraFine());
		assertEquals(salaProg, prog.getSalaProgrammazione());
		assertEquals(f,prog.getFilm());
	}

	@Test
	public void testGetIdProgrammazione() {
		prog.setIdProgrammazione(2);
		assertEquals(2, prog.getIdProgrammazione());
	}

	@Test
	public void testGetGiorno() {
		giorno_1 = LocalDateTime.now();
		prog.setGiorno(giorno);
		assertEquals(giorno, prog.getGiorno());
	}

	@Test
	public void testGetOraInizio() {
		oraInizio_1 = LocalTime.now();
		prog.setOraInizio(oraInizio_1);
		assertEquals(oraInizio_1, prog.getOraInizio());
	}

	@Test
	public void testGetOraFine() {
		oraFine_1 = LocalTime.now();
		prog.setOraFine(oraFine_1);
		assertEquals(oraFine_1, prog.getOraFine());
	}

	@Test
	public void testGetPrenotazioni() {
		prog.setPrenotazioni(prenotazioni);
		assertEquals(prenotazioni, prog.getPrenotazioni());
	}

	@Test
	public void testGetSalaProgrammazione() {
		salaProg_1 = new Sala(2, "B", 2, 3);
		prog.setSalaProgrammazione(salaProg_1);
	}

	@Test
	public void testGetFilm() {
		f.setGenere("western");
		f_1 = f;
		prog.setFilm(f_1);
		assertEquals(f_1, prog.getFilm());
	}

	@Test
	public void testAggiungiPrenotazione() {
		/* La prenotazione e' aggiunta correttamente e verifico che i posti
		 * risultino effettivamente prenotati nella sala.
		 */
		assertTrue(prog.aggiungiPrenotazione(p));
		int fila[] = p.getFilaPosti();
		int colonna[] = p.getColonnaPosti();
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila[0], colonna[0]));
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila[1], colonna[1]));
		/* La prenotazione non si può aggiungere se i posti
		  scelti risultano già prenotati o la sala non dispone
		  di abbastanza posti liberi */
		assertFalse(prog.aggiungiPrenotazione(p));
		/*provo ad aggiungere la stessa prenotazione per verificare che
		 * non sia possibile (i posti sono già prenotati)
		 */
	}

	@Test
	public void testCancellaPrenotazione() {
		giorno = LocalDateTime.now();
		oraInizio = LocalTime.now();
		prog.setGiorno(giorno);
		prog.setOraInizio(oraInizio);
		Prenotazione p_2 = new Prenotazione(3, 2, 5.50, prog);
		p_2.setColonnaPosti(colonnaPosti);
		p_2.setFilaPosti(filaPosti);
		prenotazioni.add(p_2);
		prog.setPrenotazioni(prenotazioni);
		//id prenotazione da cancellare errato
		assertFalse(prog.cancellaPrenotazione(5));
		//test verifica ora cancellazione non superato
		assertFalse(prog.cancellaPrenotazione(3));
		//programmazione rimossa
		giorno = LocalDateTime.of(2019, Month.SEPTEMBER,
				  25, 00, 00, 00);
		prog.setGiorno(giorno);
		assertTrue(prog.cancellaPrenotazione(1));
		assertFalse(prog.getPrenotazioni().contains(p));
		
	}


	@Test
	public void testVerificaOraCancellazione() {
		/*spettacolo già avvenuto la prenotazione
		 * non può essere rimossa e viene conservata
		 * nello storico delle prenotazioni del cliente.
		 */
		assertFalse(prog.verificaOraCancellazione());
		//giorno della proiezione successivo
		giorno = LocalDateTime.of(2019, Month.SEPTEMBER,
				  25, 00, 00, 00);
		prog.setGiorno(giorno);
		//assertTrue(prog.verificaOraCancellazione());
		//stesso giorno, ma con meno di un'ora di anticipo
		giorno = LocalDateTime.now();
		oraInizio = LocalTime.now();
		prog.setGiorno(giorno);
		prog.setOraInizio(oraInizio);
		assertFalse(prog.verificaOraCancellazione());
		/* stesso giorno con più di un'ora di anticipo sulla
		 * proiezione del film prenotato
		 */
		giorno = LocalDateTime.now();
		oraInizio = LocalTime.now().plusHours(2);
		prog.setGiorno(giorno);
		prog.setOraInizio(oraInizio);
		assertTrue(prog.verificaOraCancellazione());
	}
	
	@Test
	public void testEquals() {
		Programmazione p_1 = new Programmazione(1, giorno, oraInizio, oraFine,
				 salaProg, f);
		Programmazione p_2 = null;
		//stesso oggetto
		assertTrue(prog.equals(prog));
		//oggetti diversi con in comune id (valore univoco).
		assertTrue(prog.equals(p_1));
		//diverso id
		p_1.setIdProgrammazione(38);;
		assertFalse(prog.equals(p_1));
		//oggetti diversi o nulli
		assertFalse(prog.equals(11));
		assertFalse(prog.equals(p_2));
	}
}
