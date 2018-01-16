package it.test.funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Circuito;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Manager;

public class UC3 {
	Circuito circuito;
	Manager m;
		
	@Before
	public void setUp() throws Exception {
		m = new Manager(1, "Kevin", "bianchi", "M", "email@g.com", false, "pass",
				"pass", "K.bianchi", "frase", 50, 2048.99);
		circuito = new Circuito("cinemaCircuito", m);
	}

	//scenario principale: registrazione con successo
	@Test
	public void testRegistrazione_scenarioPrincipale() {
		CartaDiCredito carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
		Cliente c = new Cliente(1, "Sara", "Rossi", "F", "e@g.com", false, "pass",
				"pass", "s.rossi", "frase", "290120", carta, null);
		circuito.registrazione(c);
		assertEquals(1, circuito.getClienti().size());
		assertTrue(circuito.getClienti().contains(c));
	}
	
	//scenario alternativo: username gia' in uso (4A)
		@Test
		public void testRegistrazione_scenarioAlternativo_4A() {
			CartaDiCredito carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
			Cliente c = new Cliente(1, "Sara", "Rossi", "F", "e@g.com", false, "pass",
					"pass", "s.rossi", "frase", "290120", carta, null);
			Cliente c_1 = new Cliente(2, "Luca", "Rossi", "M", "e@g.com", false, "pass",
					"pass", "lucarossi", "frase", "290120", carta, null);
			ArrayList<Cliente> clienti = new ArrayList<Cliente>();
			clienti.add(c);
			clienti.add(c_1);
			circuito.setClienti(clienti);
			circuito.registrazione(c);
			assertEquals(2, circuito.getClienti().size());
		}
		
		//scenario alternativo: le due password inserite non 
		//risultano coincidenti (4B)
		@Test
		public void testRegistrazione_scenarioAlternativo_4B() {
			CartaDiCredito carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
			Cliente c = new Cliente(1, "Sara", "Rossi", "F", "e@g.com", false, "pass",
					"passVerificaErrata", "s.rossi", "frase", "290120", carta, null);
			circuito.registrazione(c);
			assertEquals(0, circuito.getClienti().size());
			assertFalse(circuito.getClienti().contains(c));
		}
		
		//scenario alternativo: la email non è conforme 
		//allo standard nomeutente@dominio (4C)
		@Test
		public void testRegistrazione_scenarioAlternativo_4C() {
			CartaDiCredito carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
			Cliente c = new Cliente(1, "Sara", "Rossi", "F", "eg.com", false, "pass",
					"passVerificaErrata", "s.rossi", "frase", "290120", carta, null);
			circuito.registrazione(c);
			assertEquals(0, circuito.getClienti().size());
			assertFalse(circuito.getClienti().contains(c));
		}
		
		//scenario alternativo: la carta di credito
		//presenta un numero non conforme (4D)
		@Test
		public void testRegistrazione_scenarioAlternativo_4D() {
			CartaDiCredito carta = new CartaDiCredito("ab18k929l",78.99);
			Cliente c = new Cliente(1, "Sara", "Rossi", "F", "eg.com", false, "pass",
					"passVerificaErrata", "s.rossi", "frase", "290120", carta, null);
			circuito.registrazione(c);
			assertEquals(0, circuito.getClienti().size());
			assertFalse(circuito.getClienti().contains(c));
		}

}
