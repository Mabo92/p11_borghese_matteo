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

public class UC8 {
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

	// Scenario Principale: cancellare prenotazioni
	@Test
	public void testCancellaPrenotazioni_scenarioPrincipale() {
		int size = p.getProgrammazione().getPrenotazioni().size();
		int size_1 = c.getPrenotazioni().size();
		giorno = LocalDateTime.of(2019, Month.SEPTEMBER,
				25, 00, 00, 00);
		prog.setGiorno(giorno);
		p.setProgrammazione(prog);
		/* scenario Alternativo: il pagamento è stato effetuato
		 *  con carta di credito (9A) */
		c.CancellaPrenotazioneCliente(p, "carta di credito");
		assertEquals(89.99, c.getCarta().getSaldo(), 0);
		assertEquals(size - 1, p.getProgrammazione().getPrenotazioni().size());
		assertEquals(size_1 - 1, c.getPrenotazioni().size());
		/* scenario Alternativo: il pagamento è stato effetuato
		 *  con abbonamento (9B) */
		c.getPrenotazioni().add(p);
		p.getProgrammazione().getPrenotazioni().add(p);
		size = p.getProgrammazione().getPrenotazioni().size();
		size_1 = c.getPrenotazioni().size();
		giorno = LocalDateTime.of(2019, Month.SEPTEMBER,
				  25, 00, 00, 00);
		prog.setGiorno(giorno);
		p.setProgrammazione(prog);
		c.CancellaPrenotazioneCliente(p, "abbonamento");
		assertEquals(12, c.getAbbonamento().getnBigliettiRimanenti());
		assertEquals(size - 1, p.getProgrammazione().getPrenotazioni().size());
		assertEquals(size_1 - 1, c.getPrenotazioni().size());
	}
	
	/* Scenario alternativo: il sistema non può eliminare
	 * la programmazione (6A)
	 */
	@Test
	public void testCancellaPrenotazioni_scenarioAlternativo_6A() {
		/* superato orario utile per cancellare 
		* la prenotazione (proiezione film già avvenuta) */
		c.CancellaPrenotazioneCliente(p, "carta di credito");
		c.CancellaPrenotazioneCliente(p, "abbonamento");
		assertEquals(prog.getPrenotazioni().size(), c.getPrenotazioni().size());
	}
}
