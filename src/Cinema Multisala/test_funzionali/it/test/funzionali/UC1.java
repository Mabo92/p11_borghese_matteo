package it.test.funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Circuito;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Manager;

public class UC1 {
	Circuito circuito;
	Manager m;
	Cliente c1;
	CartaDiCredito carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
	
	@Before
	public void setUp() throws Exception {
		m = new Manager(1, "Kevin", "bianchi", "M", "email@g.com", false, "pass",
				"pass", "K.bianchi", "frase", 50, 2048.99);
		c1 = new Cliente(1, "Sara", "Rossi", "F", "e@g.com", false, "pass",
				"pass", "s.rossi", "frase", "290120", carta, null);
		circuito = new Circuito("cinemaCircuito", m);
		ArrayList<Cliente> clienti = new ArrayList<Cliente>();
		clienti.add(c1);
		circuito.setClienti(clienti);
	}
	
	//Scenario principale: autenticazione con successo.
	@Test
	public void testAutenticarsi_scenarioPrincipale() {
		//Attore cliente
		circuito.login(c1.getNomeUtente(), c1.getPassword());
		assertTrue(c1.isLoggato());
		//Attore manager
		circuito.login(m.getNomeUtente(), m.getPassword());
		assertTrue(m.isLoggato());
	}
	
	//scenario alternativo: autenticazione fallita (4A)
		@Test
		public void testAutenticarsi_scenarioAlternativo_4A() {
			//Attore cliente
			circuito.login(c1.getNomeUtente(), "sbagliata");
			assertFalse(c1.isLoggato());
			//Attore manager
			circuito.login(m.getNomeUtente(), "sbagliata");
			assertFalse(m.isLoggato());
		}
		
	//scenario alternativo: autenticazione fallita (4B)
		@Test
		public void testAutenticarsi_scenarioAlternativo_4B() {
			//Attore cliente
			circuito.login("sbagliato", c1.getPassword());
			assertFalse(c1.isLoggato());
			circuito.login("sbagliato", "sbagliata");
			assertFalse(c1.isLoggato());
			//Attore manager
			circuito.login("sbagliato", m.getPassword());
			assertFalse(c1.isLoggato());
			circuito.login("sbagliato", "sbagliata");
			assertFalse(c1.isLoggato());
		}

}
