package it.test.funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Cinema;
import it.cinema.multisala.Circuito;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Manager;

public class UC19 {
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

	/* scenario principale: aggiungere nuovi cinema al circuito eseguito
	 * con successo
	 */
	@Test
	public void testAggiungereNuoviCinemaAlCircuito_scenarioPrincipale() {
		Cinema cine = new Cinema(1, "Fiumara", "Genova", "Via...");
		Cinema cine_2 = new Cinema(2, "TheSpace", "Genova", "Corso...");
		Cinema cine_3 = new Cinema(3, "TheSpace", "Milano", "Via...");
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		cinemas.add(cine);
		cinemas.add(cine_2);
		circuito.setCinemaDelCircuito(cinemas);
		//aggiungo un nuovo cinema non esistente correttamente
		assertTrue(circuito.aggiungereCinema(cine_3));
		assertTrue(circuito.getCinemaDelCircuito().contains(cine_3));
	}
	
	/* scenario secondario: i dati inseiti non sono corretti (6A) */
	@Test
	public void testAggiungereNuoviCinemaAlCircuito_scenarioSecondario_6A() {
		Cinema cine = new Cinema(1, "Fiumara", "Genova", "Via...");
		Cinema cine_2 = new Cinema(2, "TheSpace", "Genova", "Corso...");
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		cinemas.add(cine);
		cinemas.add(cine_2);
		circuito.setCinemaDelCircuito(cinemas);
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
	}
	
	/* scenario Secondario: il cinema esiste già nel circuito (6B) */
	@Test
	public void testAggiungereNuoviCinemaAlCircuito_scenarioSecondario_6B() {
		Cinema cine = new Cinema(1, "Fiumara", "Genova", "Via...");
		Cinema cine_2 = new Cinema(2, "TheSpace", "Genova", "Corso...");
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
	}

}
