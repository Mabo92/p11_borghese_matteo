package it.test.strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.cinema.multisala.Utente;

public class UtenteTest {

	Utente u;
	
	@Before
	public void setUp() throws Exception {
		u = new Utente(1, "Marco", "Rossi", "M", "mr@gmail.com", false, "password",
					   "password", "mRossi", "frase");
	}

	@Test
	public void testUtente() {
		assertNotNull(u);
		assertEquals(1,u.getId());
		assertEquals("Marco",u.getNome());
		assertEquals("Rossi",u.getCognome());
		assertEquals("mr@gmail.com",u.getEmail());
		assertEquals("M",u.getSesso());
		assertFalse(u.isLoggato());
		assertEquals("password",u.getPassword());
		assertEquals("password",u.getPasswordVerifica());
		assertEquals("mRossi", u.getNomeUtente());
		assertEquals("frase", u.getFraseSegreta());
	}

	@Test
	public void testGetId() {
		u.setId(2);
		assertEquals(2, u.getId());
	}

	@Test
	public void testGetNome() {
		u.setNome("Luca");
		assertEquals("Luca", u.getNome());
	}

	@Test
	public void testGetCognome() {
		u.setCognome("Red");
		assertEquals("Red", u.getCognome());
	}
	
	@Test
	public void testGetSesso() {
		u.setSesso("F");
		assertEquals("F", u.getSesso());
	}
	
	@Test
	public void testGetEmail() {
		u.setEmail("email@g.com");
		assertEquals("email@g.com", u.getEmail());
	}

	@Test
	public void testGetPassword() {
		u.setPassword("p");
		assertEquals("p", u.getPassword());
	}

	@Test
	public void testGetPasswordVerifica() {
		u.setPasswordVerifica("p");
		assertEquals("p", u.getPasswordVerifica());
	}

	@Test
	public void testGetNomeUtente() {
		u.setNomeUtente("username");
		assertEquals("username", u.getNomeUtente());
	}

	@Test
	public void testGetFraseSegreta() {
		u.setFraseSegreta("f");
		assertEquals("f", u.getFraseSegreta());
	}

	@Test
	public void testIsLoggato() {
		u.setLoggato(true);
		assertTrue(u.isLoggato());
	}

}
