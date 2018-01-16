package it.test.strutturali;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Cinema;
import it.cinema.multisala.Circuito;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Film;
import it.cinema.multisala.Manager;
import it.cinema.multisala.Prenotazione;
import it.cinema.multisala.Programmazione;
import it.cinema.multisala.Sala;

@RunWith(Parameterized.class)
public class CircuitoTest {
	Circuito circuito;
	Manager m;
	
	@Parameter(0) public String password;
	@Parameter(1) public String nomeUtente;	
	@Parameter(2) public boolean expectedValueManager;
	@Parameter(3) public boolean expectedValueCliente;
	
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
	
	@Parameters
	public static Collection<Object[]> data() { 
		return Arrays.asList(new Object[][] {
			
			{"errata", "K.bianchi", false, false}, 
			{"pass", "errato", false, false},
			{"errata", "errato", false, false},
			{"pass", "K.bianchi", true, false},
			
			{"errata", "s.rossi", false, false},
			{"pass", "errato", false, false},
			{"errata", "errato", false, false},
			{"pass", "s.rossi", false, true}
		});
	}
	
	@Test
	public void testCircuito() {
		assertNotNull(circuito);
		assertEquals("cinemaCircuito", circuito.getNome());
		assertEquals(m, circuito.getManager());
	}


	@Test
	public void testAggiungereCinema() {
		Cinema cine = new Cinema(1, "Fiumara", "Genova", "Via...");
		Cinema cine_2 = new Cinema(2, "TheSpace", "Genova", "Corso...");
		Cinema cine_3 = new Cinema(3, "TheSpace", "Milano", "Via...");
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		cinemas.add(cine);
		cinemas.add(cine_2);
		circuito.setCinemaDelCircuito(cinemas);
		/*
		 * Provo ad aggiungere al circuito un cinema
		 * gia presente -> false
		 */
		assertFalse(circuito.aggiungereCinema(cine));
		assertEquals(circuito.getCinemaDelCircuito().size(), cinemas.size());
		//non inserisco campo nome
		cine.setIdCinema(5);
		cine.setNome(null);
		assertFalse(circuito.aggiungereCinema(cine));
		assertEquals(circuito.getCinemaDelCircuito().size(), cinemas.size());
		//non inserisco campo citta
		cine.setNome("Fiumara");
		cine.setCitta(null);
		assertFalse(circuito.aggiungereCinema(cine));
		assertEquals(circuito.getCinemaDelCircuito().size(), cinemas.size());
		//non inserisco ne il nome ne la citta
		//non inserisco campo citta
		cine.setNome(null);
		cine.setCitta(null);
		assertFalse(circuito.aggiungereCinema(cine));
		assertEquals(circuito.getCinemaDelCircuito().size(), cinemas.size());
		//aggiungo un nuovo cinema non esistente correttamente
		assertTrue(circuito.aggiungereCinema(cine_3));
		assertTrue(circuito.getCinemaDelCircuito().contains(cine_3));
	}

	@Test
	public void testEliminaCinema() {
		Cinema cine = new Cinema(1, "Fiumara", "Genova", "Via...");
		Cinema cine_2 = new Cinema(2, "TheSpace", "Genova", "Corso...");
		Cinema cine_3 = new Cinema(3, "TheSpace", "Milano", "Via...");
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
		//provo ad eliminare un cinema inesistente -> false
		assertFalse(circuito.eliminaCinema(cine_3.getIdCinema()));
		assertEquals(circuito.getCinemaDelCircuito().size(), cinemas.size());
		//provo ad eliminare un cinema con delle prenotazioni attive -> false
		assertFalse(circuito.eliminaCinema(cine_2.getIdCinema()));
		assertEquals(circuito.getCinemaDelCircuito().size(), cinemas.size());
		prenotazioni = new ArrayList<Prenotazione>();
		prog.setPrenotazioni(prenotazioni);
		cine.aggiungiProgrammazioneFilm(prog);
		cinemas = new ArrayList<Cinema>();
		cinemas.add(cine);
		cinemas.add(cine_2);
		circuito.setCinemaDelCircuito(cinemas);
		//elimino un cinema dal circuito
		assertTrue(circuito.eliminaCinema(cine.getIdCinema()));
		assertFalse(circuito.getCinemaDelCircuito().contains(cine));
	}

	@Test
	public void testLogin() {
		circuito.login(nomeUtente, password);
		assertEquals(expectedValueManager,circuito.getManager().isLoggato());
		assertEquals(expectedValueCliente, circuito.getClienti().get(0).isLoggato());
	}

	@Test
	public void testLogout() {
		circuito.getManager().setLoggato(true);
		circuito.getClienti().get(0).setLoggato(true);
		//nome utente errati
		circuito.logout("sbssbd");
		assertTrue(circuito.getManager().isLoggato());
		assertTrue(circuito.getClienti().get(0).isLoggato());
		//nome utente manager corretto
		circuito.logout("K.bianchi");
		assertFalse(circuito.getManager().isLoggato());
		//nome utente manager corretto
		circuito.logout("s.rossi");
		assertFalse(circuito.getClienti().get(0).isLoggato());
	}

	@Test
	public void testRegistrazione() {
		CartaDiCredito carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
		CartaDiCredito carta_1 = new CartaDiCredito("929j9991919l",78.99);
		Cliente c = new Cliente(1, "Sara", "Rossi", "F", "e@g.com", false, "pass",
				"pass", "s.rossi", "frase", "290120", carta, null);
		//password diversa password di verifica
		Cliente c_1 = new Cliente(2, "Luca", "Rossi", "F", "e@g.com", false, "pass",
				"pass1", "l.rossi", "frase", "290120", carta, null);
		//email non conforme allo standard
		Cliente c_2 = new Cliente(2, "Luca", "Rossi", "F", "g.com", false, "pass",
				"pass", "l.rossi", "frase", "290120", carta, null);
		//numero della carta di credito errato
		Cliente c_3 = new Cliente(2, "Luca", "Rossi", "F", "e@g.com", false, "pass",
				"pass", "l.rossi", "frase", "290120", carta_1, null);
		//corretto
		Cliente c_4 = new Cliente(2, "Luca", "Rossi", "F", "e@g.com", false, "pass",
				"pass", "l.rossi", "frase", "290120", carta, null);
		circuito.registrazione(c);
		assertEquals(1,circuito.getClienti().size());
		circuito.registrazione(c_1);
		assertEquals(1,circuito.getClienti().size());
		circuito.registrazione(c_2);
		assertEquals(1,circuito.getClienti().size());
		circuito.registrazione(c_3);
		assertEquals(1,circuito.getClienti().size());
		circuito.registrazione(c_4);
		assertEquals(2,circuito.getClienti().size());
		assertTrue(circuito.getClienti().contains(c_4));
		
	}

}
