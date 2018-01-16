package it.test.funzionali;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Circuito;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Manager;

public class UC2 {
	Circuito circuito;
	Manager m;
	Cliente c1;
	CartaDiCredito carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
	
	@Before
	public void setUp() throws Exception {
		m = new Manager(1, "Kevin", "bianchi", "M", "email@g.com", true, "pass",
				"pass", "K.bianchi", "frase", 50, 2048.99);
		c1 = new Cliente(1, "Sara", "Rossi", "F", "e@g.com", true, "pass",
				"pass", "s.rossi", "frase", "290120", carta, null);
		circuito = new Circuito("cinemaCircuito", m);
		ArrayList<Cliente> clienti = new ArrayList<Cliente>();
		clienti.add(c1);
		circuito.setClienti(clienti);
	}
	
	//Scenario principale: logout corretto
	@Test
	public void testLogout_scenarioPrincipale() {
		//Attore cliente
		circuito.logout(c1.getNomeUtente());
		assertFalse(c1.isLoggato());
		//Attore manager
		circuito.logout(m.getNomeUtente());
		assertFalse(m.isLoggato());
	}

}
