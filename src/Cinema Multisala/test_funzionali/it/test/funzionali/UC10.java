package it.test.funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.Abbonamento;
import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Manager;

public class UC10 {
	Manager m;
	Cliente c;
	Abbonamento a;
	CartaDiCredito carta;
	
	@Before
	public void setUp() throws Exception {
		m = new Manager(1, "Kevin", "bianchi", "M", "email@g.com", true, "pass",
				"pass", "K.bianchi", "frase", 50, 2048.99);
		a = new Abbonamento("standard", 10, 55.50);
		carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
		c = new Cliente(1, "Marco", "Rossi", "M", "a@g.it", true, "pass",
				"pass", "m.rossi", "frase", "555555", carta, a);
	}

	//Scenario principale: modificare dati personali avvenuto con successo
	@Test
	public void testModificareDatiPersonali_scenarioPrincipale() {
		//attore cliente
		c.setNome("Laura");
		assertEquals("Laura",c.getNome());
		c.setCognome("Bianchi");
		assertEquals("Bianchi",c.getCognome());
		c.setSesso("F");
		assertEquals("F",c.getSesso());
		c.setFraseSegreta("fraseSegreta");
		assertEquals("fraseSegreta", c.getFraseSegreta());
		c.setNumeroDiTelefono("0000000");
		assertEquals("0000000", c.getNumeroDiTelefono());
		assertTrue(c.modificaDatiPersonali("rrr@ggg.com", "password", "password",
				"nasjfsnfj899999jds"));
		assertEquals("rrr@ggg.com", c.getEmail());
		assertEquals("password", c.getPassword());
		assertEquals("password", c.getPasswordVerifica());
		assertEquals("nasjfsnfj899999jds", c.getCarta().getNumeroCarta());
		//attore Manager
		m.setNome("Laura");
		assertEquals("Laura",m.getNome());
		m.setCognome("Bianchi");
		assertEquals("Bianchi",m.getCognome());
		m.setSesso("F");
		assertEquals("F",m.getSesso());
		m.setFraseSegreta("fraseSegreta");
		assertEquals("fraseSegreta", m.getFraseSegreta());
		m.setNumeroDipendenti(200);
		assertEquals(200, m.getNumeroDipendenti());
		m.setStipendio(123.56);
		assertEquals(123.56, m.getStipendio(), 0);
		assertTrue(m.modificaDatiPersonali("rrr@ggg.com", "password", "password"));
		assertEquals("rrr@ggg.com", m.getEmail());
		assertEquals("password", m.getPassword());
		assertEquals("password", m.getPasswordVerifica());
	}
	
	//Scenario alternativo: il sistema rileva un errore sio dati (5A)
		@Test
		public void testModificareDatiPersonali_scenarioAlternativo_5A() {
			//attore cliente
			c.setNome("Laura");
			assertEquals("Laura",c.getNome());
			c.setCognome("Bianchi");
			assertEquals("Bianchi",c.getCognome());
			c.setSesso("F");
			assertEquals("F",c.getSesso());
			c.setFraseSegreta("fraseSegreta");
			assertEquals("fraseSegreta", c.getFraseSegreta());
			c.setNumeroDiTelefono("0000000");
			assertEquals("0000000", c.getNumeroDiTelefono());
			//email errata
			assertFalse(c.modificaDatiPersonali("rrrggg.com", "password", "password",
					"nasjfsnfj899999jds"));
			//password digitata errata
			assertFalse(c.modificaDatiPersonali("rrr@ggg.com", "password", "password11",
					"nasjfsnfj899999jds"));
			//numero carta di credito errato
			assertFalse(c.modificaDatiPersonali("rrr@ggg.com", "password", "password",
					"nasjfsn"));
			//attore Manager
			m.setNome("Laura");
			assertEquals("Laura",m.getNome());
			m.setCognome("Bianchi");
			assertEquals("Bianchi",m.getCognome());
			m.setSesso("F");
			assertEquals("F",m.getSesso());
			m.setFraseSegreta("fraseSegreta");
			assertEquals("fraseSegreta", m.getFraseSegreta());
			m.setNumeroDipendenti(200);
			assertEquals(200, m.getNumeroDipendenti());
			m.setStipendio(123.56);
			assertEquals(123.56, m.getStipendio(), 0);
			//email errata
			assertFalse(m.modificaDatiPersonali("rrrggg.com", "password", "password"));
			//password digitata errata
			assertFalse(m.modificaDatiPersonali("rrr@ggg.com", "password", "password11"));			
		}
}
