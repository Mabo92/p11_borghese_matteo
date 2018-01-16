package it.test.funzionali;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Cinema;
import it.cinema.multisala.Circuito;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Film;
import it.cinema.multisala.Manager;
import it.cinema.multisala.Prenotazione;
import it.cinema.multisala.Programmazione;
import it.cinema.multisala.Sala;

public class UC20 {
	Circuito circuito;
	Manager m;

	@Before
	public void setUp() throws Exception {
		m = new Manager(1, "Kevin", "bianchi", "M", "email@g.com", false, "pass",
				"pass", "K.bianchi", "frase", 50, 2048.99);
		circuito = new Circuito("cinemaCircuito", m);
		CartaDiCredito carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
		Cliente c = new Cliente(1, "Sara", "Rossi", "F", "e@g.com", false, "pass",
				"pass", "s.rossi", "frase", "290120", carta, null);
		ArrayList<Cliente> clienti = new ArrayList<Cliente>();
		clienti.add(c);
		circuito.setClienti(clienti);
	}

	// Scenario Principale: eliminare cinema dal circuito con successo
	@Test
	public void testEliminareCinemaDalCircuito_scenarioPrincipale() {
		Cinema cine = new Cinema(1, "Fiumara", "Genova", "Via...");
		Cinema cine_2 = new Cinema(2, "TheSpace", "Genova", "Corso...");
		LocalTime durata = LocalTime.of(1 , 57);
		LocalDateTime dataUscita = LocalDateTime.of(1982, Month.JUNE, 
				   25, 00, 00, 00);
		LocalDateTime dataRimozione = LocalDateTime.of(1982, Month.SEPTEMBER,
					  25, 00, 00, 00);
		Sala salaProg = new Sala(1, "A", 2, 3);
		Film f = new Film(1, "Blade Runner", "fatascienza", "Deckard è costretto...", 4.8, 
				5.50, durata, dataUscita, dataRimozione);
		LocalDateTime giorno = LocalDateTime.of(1992, Month.SEPTEMBER,
				  25, 00, 00, 00);
		LocalTime oraInizio = LocalTime.of(12, 30);
		LocalTime oraFine = oraInizio.plusHours(durata.getHour());
		oraFine = oraFine.plusMinutes(durata.getMinute());
		int filaPosti[] = new int[] {1, 1};
		int colonnaPosti[] = new int[] {1, 2};
		Programmazione prog = new Programmazione(1, giorno, oraInizio, oraFine,
				 salaProg, f);
		Prenotazione p = new Prenotazione(1, 2, 5.50, prog);
		p.setColonnaPosti(colonnaPosti);
		p.setFilaPosti(filaPosti);
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		prog.setPrenotazioni(prenotazioni);
		cine.aggiungiProgrammazioneFilm(prog);
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		cinemas.add(cine);
		cinemas.add(cine_2);
		circuito.setCinemaDelCircuito(cinemas);
		//elimino un cinema dal circuito
		assertTrue(circuito.eliminaCinema(cine.getIdCinema()));
		assertFalse(circuito.getCinemaDelCircuito().contains(cine));
	}
	
	// Scenario secondario: Esistono delle prenotazioni (6A)
		@Test
		public void testEliminareCinemaDalCircuito_scenarioSecondario_6A() {
			Cinema cine = new Cinema(1, "Fiumara", "Genova", "Via...");
			Cinema cine_2 = new Cinema(2, "TheSpace", "Genova", "Corso...");
			LocalTime durata = LocalTime.of(1 , 57);
			LocalDateTime dataUscita = LocalDateTime.of(1982, Month.JUNE, 
					   25, 00, 00, 00);
			LocalDateTime dataRimozione = LocalDateTime.of(1982, Month.SEPTEMBER,
						  25, 00, 00, 00);
			Sala salaProg = new Sala(1, "A", 2, 3);
			Film f = new Film(1, "Blade Runner", "fatascienza", "Deckard è costretto...", 4.8, 
					5.50, durata, dataUscita, dataRimozione);
			LocalDateTime giorno = LocalDateTime.of(1992, Month.SEPTEMBER,
					  25, 00, 00, 00);
			LocalTime oraInizio = LocalTime.of(12, 30);
			LocalTime oraFine = oraInizio.plusHours(durata.getHour());
			oraFine = oraFine.plusMinutes(durata.getMinute());
			int filaPosti[] = new int[] {1, 1};
			int colonnaPosti[] = new int[] {1, 2};
			Programmazione prog = new Programmazione(1, giorno, oraInizio, oraFine,
					 salaProg, f);
			Prenotazione p = new Prenotazione(1, 2, 5.50, prog);
			p.setColonnaPosti(colonnaPosti);
			p.setFilaPosti(filaPosti);
			ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
			prenotazioni.add(p);
			prog.setPrenotazioni(prenotazioni);
			cine_2.aggiungiProgrammazioneFilm(prog);
			ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
			cinemas.add(cine);
			cinemas.add(cine_2);
			circuito.setCinemaDelCircuito(cinemas);
			//provo ad eliminare un cinema con delle prenotazioni attive -> false
			assertFalse(circuito.eliminaCinema(cine_2.getIdCinema()));
			assertEquals(circuito.getCinemaDelCircuito().size(), cinemas.size());
		}

}
