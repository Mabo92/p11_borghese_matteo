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

public class PrenotazioneTest {

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
	}

	
	@Test
	public void testPrenotazione() {
		assertNotNull(p);
		assertEquals(1,p.getIdPrenotazione());
		assertEquals(2, p.getNumeroDiBiglietti());
		assertArrayEquals(filaPosti, p.getFilaPosti());
		assertArrayEquals(colonnaPosti, p.getColonnaPosti());
		assertEquals(5.50, p.getCostoSingolo(), 0);
		assertEquals(prog, p.getProgrammazione());
	}

	@Test
	public void testGetIdPrenotazione() {
		p.setIdPrenotazione(2);
		assertEquals(2, p.getIdPrenotazione());
	}

	@Test
	public void testGetNumeroDiBiglietti() {
		p.setNumeroDiBiglietti(8);
		assertEquals(8, p.getNumeroDiBiglietti());
	}

	@Test
	public void testGetFilaPosti() {
		filaPosti = new int[] {1, 1};
		p.setFilaPosti(filaPosti);
		assertArrayEquals(filaPosti, p.getFilaPosti());
	}

	@Test
	public void testGetColonnaPosti() {
		colonnaPosti = new int[] {1, 1, 1};
		p.setColonnaPosti(colonnaPosti);
		assertArrayEquals(colonnaPosti, p.getColonnaPosti());
	}

	@Test
	public void testGetCostoSingolo() {
		p.setCostoSingolo(6.0);
		assertEquals(6.0, p.getCostoSingolo(), 0);
	}

	@Test
	public void testGetProgrammazione() {
		salaProg.setIdSala(4);
		prog = new Programmazione(5, giorno, oraInizio, oraFine,
				 salaProg, f);
		p.setProgrammazione(prog);
		assertEquals(prog, p.getProgrammazione());
	}

	@Test
	public void testCalcolaTotale() {
		assertEquals(11.0, p.calcolaTotale(), 0);
	}
	
}
