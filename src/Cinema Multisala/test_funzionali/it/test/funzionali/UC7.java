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

public class UC7 {
	
	Cliente c;
	Abbonamento a;
	CartaDiCredito carta;
	Programmazione prog;
	Film f;
	Sala salaProg;
	LocalDateTime giorno;
	LocalTime oraInizio;
	LocalTime oraFine;
	
	@Before
	public void setUp() throws Exception {
				
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
		a = new Abbonamento("standard", 10, 55.50);
		carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
		c = new Cliente(1, "Marco", "Rossi", "M", "a@g.it", true, "pass",
				"pass", "m.rossi", "frase", "555555", carta, a);
	}

	//Scenario principale: acquistare biglietti eseguito con successo
	@Test
	public void testAcquistareBiglietti_scenarioPrincipale() {
		int filaPosti[] = new int[] {1, 1};
		int colonnaPosti[] = new int[] {1, 2};
		Prenotazione p = new Prenotazione(1, 2, 5.50, prog);
		p.setColonnaPosti(colonnaPosti);
		p.setFilaPosti(filaPosti);
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(p);
		prog.setPrenotazioni(prenotazioni);
		c.setPrenotazioni(prenotazioni);
		//prenotazione andata a buon fine carta di credito
		c.prenotaBiglietti(p, "carta di credito");
		int fila[] = p.getFilaPosti();
		int colonna[] = p.getColonnaPosti();
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila[0], colonna[0]));
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila[1], colonna[1]));
		assertEquals(67.99, c.getCarta().getSaldo(), 0);
		assertTrue(c.getPrenotazioni().contains(p));
		//resetto i prosti in sala
		salaProg = new Sala(1, "A", 2, 3);
		prog = new Programmazione(1, giorno, oraInizio, oraFine,
								 salaProg, f);
		Prenotazione pren_1 = new Prenotazione(1, 2, 5.50, prog);
		pren_1.setColonnaPosti(colonnaPosti);
		pren_1.setFilaPosti(filaPosti);
		prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(pren_1);
		prog.setPrenotazioni(prenotazioni);
		//prenotazione andata a buon fine abbonamento
		c.prenotaBiglietti(pren_1, "abbonamento");
		int fila_1[] = p.getFilaPosti();
		int colonna_1[] = p.getColonnaPosti();
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila_1[0], colonna_1[0]));
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila_1[1], colonna_1[1]));
		assertEquals(8, c.getAbbonamento().getnBigliettiRimanenti());
		assertTrue(c.getPrenotazioni().contains(pren_1));
	}
		
	/* Scenario alternativo: il numero di biglietti eccede la
	   disponibilità di posti (4A) */
	@Test
	public void testAcquistareBiglietti_scenarioAlternativo_4A() {
		int filaPosti[] = new int[7];
		int colonnaPosti[] = new int[7];
		//posti liberi in sala 6 (2 file e 3 colonne tutte libere in salaProg)
		Prenotazione p = new Prenotazione(1, 7, 1.50, prog);
		p.setColonnaPosti(colonnaPosti);
		p.setFilaPosti(filaPosti);
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(p);
		prog.setPrenotazioni(prenotazioni);
		c.setPrenotazioni(prenotazioni);
		assertEquals(78.99, c.getCarta().getSaldo(), 0);
		assertEquals(10, c.getAbbonamento().getnBigliettiRimanenti());	
	}
	
	/* Scenario alternativo: l abbonamento scelto non copre la
	   disponibilità richiesta (9A) */
	@Test
	public void testAcquistareBiglietti_scenarioAlternativo_9A() {
		int filaPosti[] = new int[] {1, 1};
		int colonnaPosti[] = new int[] {1, 2};
		c.getAbbonamento().setnBigliettiRimanenti(1);
		//eccede posti rimanenti abbonamento (1) posti prenotati (2)
		Prenotazione p = new Prenotazione(1, 2, 5.50, prog);
		p.setColonnaPosti(colonnaPosti);
		p.setFilaPosti(filaPosti);
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(p);
		prog.setPrenotazioni(prenotazioni);
		c.setPrenotazioni(prenotazioni);
		c.prenotaBiglietti(p, "abbonamento");
		//verifico che i posti siano rimasti liberi
		assertFalse(prog.getSalaProgrammazione().isPrenotato(filaPosti[0], colonnaPosti[0]));
		assertFalse(prog.getSalaProgrammazione().isPrenotato(filaPosti[1], colonnaPosti[1]));
		//dimensione abbonamento inalterata
		assertEquals(1, c.getAbbonamento().getnBigliettiRimanenti());
	}
	
	/* Scenario alternativo: la carta di credito scelta non
	   copre la spesa richiesta (9B) */
	@Test
	public void testAcquistareBiglietti_scenarioAlternativo_9B() {
		int filaPosti[] = new int[] {1, 1};
		int colonnaPosti[] = new int[] {1, 2};
		//importo richiesto 2*50.50, saldo carta 78.99
		Prenotazione p = new Prenotazione(1, 2, 50.50, prog);
		p.setColonnaPosti(colonnaPosti);
		p.setFilaPosti(filaPosti);
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(p);
		prog.setPrenotazioni(prenotazioni);
		c.setPrenotazioni(prenotazioni);
		c.prenotaBiglietti(p, "carta di credito");
		//verifico che i posti siano rimasti liberi
		assertFalse(prog.getSalaProgrammazione().isPrenotato(filaPosti[0], colonnaPosti[0]));
		assertFalse(prog.getSalaProgrammazione().isPrenotato(filaPosti[1], colonnaPosti[1]));
		//saldo carta inalterato
		assertEquals(78.99, c.getCarta().getSaldo(), 0);
	}
}
