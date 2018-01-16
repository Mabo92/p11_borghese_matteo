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

import it.cinema.multisala.Abbonamento;

@RunWith(Parameterized.class)
public class AbbonamentoTest {
	
	Abbonamento a;
	@Parameter(0) public int nBiglietti; 
	@Parameter(1) public boolean expectedValue;
	
	
	@Before
	public void setUp() throws Exception {
		a = new Abbonamento("standard", 10, 50.0);
	}
	
	@Parameters
	public static Collection<Object[]> data(){ 
		return Arrays.asList(new Object[][] {
			{3,true},
			{20,false}, 
			{5, true}
		});
	}
	
	@Test
	public void testAbbonamento() {
		assertNotNull(a); 
		assertEquals("standard",a.getTipo());
		assertEquals(10,a.getnBigliettiRimanenti());
		assertEquals(50.0,a.getCosto(),0.0);
	}

	@Test
	public void testGetTipo() {
		a.setTipo("premium");
		assertEquals("premium",a.getTipo());
	}

	@Test
	public void testGetnBigliettiRimanenti() {
		a.setnBigliettiRimanenti(2);
		assertEquals(2,a.getnBigliettiRimanenti());
	}

	@Test
	public void testGetCosto() {
		a.setCosto(30.6);
		assertEquals(30.6,a.getCosto(),0.0);
	}

	@Test
	public void testAggiornaBiglietti() {
		assertEquals(expectedValue,a.aggiornaBiglietti(nBiglietti));
	}

	@Test
	public void testRimborsaBiglietti() {
		a.setnBigliettiRimanenti(5);
		a.rimborsaBiglietti(3);
		assertEquals(8,a.getnBigliettiRimanenti());
	}
	
	@Test
	public void testEquals() {
		Abbonamento a_1 = new Abbonamento("standard", 10, 50);
		Abbonamento a_2 = null;
		//stesso oggetto
		assertTrue(a.equals(a));
		/* oggetti diversi con in comune tipo, 
		nBigliettiRimanenti e costo. */
		assertTrue(a.equals(a_1));
		//diverso tipo
		a_1.setTipo("eeee");
		assertFalse(a.equals(a_1));
		//diverso nBigliettiRimanenti
		a_1.setTipo("standard");
		a_1.setnBigliettiRimanenti(15);
		assertFalse(a.equals(a_1));
		//diverso costo
		a_1.setTipo("standard");
		a_1.setnBigliettiRimanenti(10);
		a_1.setCosto(57.90);
		assertFalse(a.equals(a_1));
		//oggetti diversi o nulli
		assertFalse(a.equals(11));
		assertFalse(a.equals(a_2));
	}
}
