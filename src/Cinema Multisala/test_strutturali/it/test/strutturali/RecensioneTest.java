package it.test.strutturali;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import it.cinema.multisala.Recensione;

@RunWith(Parameterized.class)
public class RecensioneTest {

	Recensione r;
	@Parameter(0) public int voto; 
	@Parameter(1) public String commento;
	@Parameter(2) public boolean expectedValue;
	
	@Before
	public void setUp() throws Exception {
		r = new Recensione(1, 3, "bello...", "Luca");
	}
	
	@Parameters
	public static Collection<Object[]> data(){ 
		return Arrays.asList(new Object[][] {
			{0,"bello...",false},
			{3,null,false},
			{3,"",false},
			{2,"brutto...",true}
		});
	}

	@Test
	public void testRecensione() {
		assertNotNull(r);
		assertEquals(1,r.getIdRecensione());
		assertEquals("bello...",r.getCommento());
		assertEquals(3,r.getVoto());
		assertEquals("Luca",r.getUsername());
	}

	@Test
	public void testGetIdRecensione() {
		r.setIdRecensione(2);
		assertEquals(2,r.getIdRecensione());
	}

	@Test
	public void testGetVoto() {
		r.setVoto(5);
		assertEquals(5,r.getVoto());
	}

	@Test
	public void testGetCommento() {
		r.setCommento("aaaaaaa");
		assertEquals("aaaaaaa",r.getCommento());
	}

	@Test
	public void testGetUsername() {
		r.setUsername("Gino");
		assertEquals("Gino",r.getUsername());
	}

	@Test
	public void testVerificaRecensione() {
		String com = "insulto, brutto";
		String expectedCom = "****, brutto";
		int v = 1;
		/*
		 * verifico i casi paramentrici: voto assente, commento assente, 
		 * voto e commento corretti
		 */
		assertEquals(expectedValue,r.verificaRecensione(voto, commento));
		//verifico se vengono eliminate parole illecite dai commenti
		assertTrue(r.verificaRecensione(v, com));
		assertEquals(expectedCom,r.getCommento());
	}

}
