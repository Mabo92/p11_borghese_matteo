package it.test.funzionali;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.Abbonamento;
import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Cinema;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Film;
import it.cinema.multisala.Prenotazione;
import it.cinema.multisala.Programmazione;
import it.cinema.multisala.Recensione;
import it.cinema.multisala.Sala;

public class UC4 {

	Cliente c;
	Abbonamento a;
	CartaDiCredito carta;
	Prenotazione p;
	int filaPosti[];
	int colonnaPosti[];
	ArrayList<Prenotazione> prenotazioni;
	Programmazione prog;
	Film f;
	Sala salaProg;
	LocalDateTime giorno;
	LocalTime oraInizio;
	LocalTime oraFine;
	Cinema cinema;
	ArrayList<Cinema> cinemas;
	ArrayList<Programmazione> programmazioni;
	
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
		oraFine = oraFine.plusMinutes(oraInizio.getMinute());
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
		prog.setPrenotazioni(prenotazioni);
		a = new Abbonamento("standard", 10, 55.50);
		carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
		c = new Cliente(1, "Marco", "Rossi", "M", "a@g.it", true, "pass",
				"pass", "m.rossi", "frase", "555555", carta, a);
		c.setPrenotazioni(prenotazioni);
		cinemas = new ArrayList<Cinema>();
		cinema = new Cinema(1, "Fiumara", "Genova", "Via...");
		programmazioni = new ArrayList<Programmazione>();
		programmazioni.add(prog);
		cinema.setProgrammazione(programmazioni);
		ArrayList<Sala> sala = new ArrayList<Sala>();
		sala.add(salaProg);
		cinema.setSala(sala);
		cinemas.add(cinema);
	}

	//Scenario principale: effettuare ricerche in base al
	//giorno di proiezione e alla citta con successo
	@Test
	public void testEffettuareRicerche_scenarioPrincipale() {		
		assertFalse(c.ricercaPerCitta(cinemas, cinema.getCitta(), giorno).isEmpty());
	}
	
	//Scenario principale: effettuare ricerche in base al
	//giorno di proiezione e alla citta e al cinema con successo
	@Test
	public void testEffettuareRicerche_scenarioPrincipale_1() {
		assertFalse(c.ricercaPerCinema(cinemas, cinema.getCitta(), 
				cinema.getNome(), giorno).isEmpty());
	}
	
	//Scenario principale: effettuare ricerche in base al giorno
	//di proiezione e alla citta e al cinema e titolo film con successo
	@Test
	public void testEffettuareRicerche_scenarioPrincipale_2() {
		assertFalse(c.ricercaPerFilm(cinemas, cinema.getCitta(), 
				cinema.getNome(), f.getTitolo(), giorno).isEmpty());
	}

	//Scenario alternativo: il sistema restituisce una lista
	// vuota (3A)
	@Test
	public void testEffettuareRicerche_scenarioAlternativo_3A() {
		LocalDateTime giorno_1 = LocalDateTime.of(1992, Month.SEPTEMBER,
				  27, 00, 00, 00);
		String citta_1 = "Torino";
		//non ci sono cinema in quella citta
		assertTrue(c.ricercaPerCitta(cinemas, citta_1, giorno).isEmpty());
		//non ci sono film in quel giorno
		assertTrue(c.ricercaPerCitta(cinemas, cinema.getCitta(), giorno_1).isEmpty());
	}
	
	//Scenario alternativo: il sistema restituisce una lista
	// vuota (3A)
	@Test
	public void testEffettuareRicerche_scenarioAlternativo_3A_1() {
		LocalDateTime giorno_1 = LocalDateTime.of(1992, Month.SEPTEMBER,
				  27, 00, 00, 00);
		Cinema cinema_1 = new Cinema(2, "oden", "Torino", "Via...");
		//nella citta scelta non esiste il cinema indicato
		assertTrue(c.ricercaPerCinema(cinemas, cinema.getCitta(), 
				cinema_1.getNome(), giorno).isEmpty());
		assertTrue(c.ricercaPerCinema(cinemas, cinema_1.getCitta(), 
		        cinema.getNome(), giorno).isEmpty());
		//nessun film in programmazione nel cinema e nel girono richiesto
		assertTrue(c.ricercaPerCinema(cinemas, cinema.getCitta(), 
				cinema.getNome(), giorno_1).isEmpty());
	}
	
	//Scenario alternativo: il sistema restituisce una lista
	// vuota (3A)
	@Test
	public void testEffettuareRicerche_scenarioAlternativo_3A_2() {
		LocalDateTime giorno_1 = LocalDateTime.of(1992, Month.SEPTEMBER,
				  27, 00, 00, 00);
		Cinema cinema_1 = new Cinema(2, "oden", "Torino", "Via...");
		String titoloFilm = "Il padrino";
		//nella citta scelta non esiste il cinema indicato
		assertTrue(c.ricercaPerFilm(cinemas, cinema.getCitta(), 
				cinema_1.getNome(),f.getTitolo(), giorno).isEmpty());
		assertTrue(c.ricercaPerFilm(cinemas, cinema_1.getCitta(), 
				cinema.getNome(),f.getTitolo(), giorno).isEmpty());
		//il film non e' in programmazione nel cinema e nel girono richiesto
		assertTrue(c.ricercaPerFilm(cinemas, cinema.getCitta(), 
				cinema.getNome(), f.getTitolo(), giorno_1).isEmpty());
		assertTrue(c.ricercaPerFilm(cinemas, cinema.getCitta(), 
				cinema.getNome(), titoloFilm, giorno).isEmpty());
		
		}
}
