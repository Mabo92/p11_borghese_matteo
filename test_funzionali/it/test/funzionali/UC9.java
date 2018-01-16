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
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Film;
import it.cinema.multisala.Prenotazione;
import it.cinema.multisala.Programmazione;
import it.cinema.multisala.Recensione;
import it.cinema.multisala.Sala;

public class UC9 {
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
	}
	
	//Scenario principale: acquistare abbonamenti eseguito con successo
	@Test
	public void testAcquistareAbbonamenti_scenarioPrincipale() {
		double saldoCarta = c.getCarta().getSaldo();
		double costoAbbonamento = c.getAbbonamento().getCosto();
		double saldoAggiornato = saldoCarta - costoAbbonamento;
		c.setAbbonamento(null);
		c.acquistaAbbonamento(a);
		assertEquals(saldoAggiornato, 
				c.getCarta().getSaldo(), 0);
	}
	
	//Scenario alternativo: la transazione non va a buon fine (7A)
	@Test
	public void testAcquistareAbbonamenti_scenarioAlternativo_7A() {
		double saldoCarta = c.getCarta().getSaldo();
		//il cliente dispone gia di un abbonamento
		c.acquistaAbbonamento(a);
		assertNotNull(c.getAbbonamento());
		//non posso acquistarne un altro, saldo carta invariato
		assertEquals(saldoCarta, 
				c.getCarta().getSaldo(), 0);
		//non dispongo di denaro sufficiente per acquistare
		c.setAbbonamento(null);
		c.getCarta().setSaldo(10.00);
		c.acquistaAbbonamento(a);
		//acquisto non effettuato, saldo carta invariato
		assertEquals(10.00, 
				c.getCarta().getSaldo(), 0);
		assertNull(c.getAbbonamento());
		
	}

}
