package it.test.funzionali;

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

public class UC13 {
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

	/* Scenario principale: eliminare un film dalla programmazione
	 * avvenuto con successo
	 */
	@Test
	public void testEliminareUnFilmDallaProgrammazione_scenarioP() {
		//rimuove programmazione esistente
		assertTrue(c.rimuoviProgrammazioneFilm(prog.getIdProgrammazione()));
		assertFalse(c.getProgrammazione().contains(prog));
	}
	
	// Scenario Alternativo: il sistema mostra una lista vuota (5A)
	@Test
	public void testEliminareUnFilmDallaProgrammazione_scenarioA_5A() {
		//la programmazione esiste ma la data di rimovione del film è 
		// successiva a quella attuale, impossibile rimuovere la prog.
		LocalDateTime dataRimozione = LocalDateTime.of(2222, Month.SEPTEMBER,
				  25, 00, 00, 00);
		prog_1.getFilm().setDataRimozione(dataRimozione);
		assertFalse(c.rimuoviProgrammazioneFilm(prog_1.getIdProgrammazione()));
		assertTrue(c.getProgrammazione().contains(prog_1));
	}

}
