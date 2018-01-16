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

import it.cinema.multisala.CartaDiCredito;

@RunWith(Parameterized.class)
public class CartaDiCreditoTest {
	
	CartaDiCredito c;
	@Parameter(0) public double importo; 
	@Parameter(1) public boolean expectedValue;

	@Before
	public void setUp() throws Exception {
		c = new CartaDiCredito("ab18k9292929j9991919l",78.99);
	}
	
	@Parameters
	public static Collection<Object[]> data(){ 
		return Arrays.asList(new Object[][] {
			{55.79,true},
			{80.00,false}, 
			{5.00, true}
		});
	}
	
	@Test
	public void testCartaDiCredito() {
		assertNotNull(c); 
		assertEquals("ab18k9292929j9991919l",c.getNumeroCarta());
		assertEquals(78.99,c.getSaldo(),0);
	}

	@Test
	public void testGetNumeroCarta() {
		c.setNumeroCarta("XXXXXX-XXXXXXXXXXX9292");
		assertEquals("XXXXXX-XXXXXXXXXXX9292", c.getNumeroCarta());
	}

	@Test
	public void testGetSaldo() {
		c.setSaldo(56.02);
		assertEquals(56.02, c.getSaldo(), 0);
	}

	@Test
	public void testPagamento() {
		assertEquals(expectedValue,c.pagamento(importo));
	}


	@Test
	public void testRimborso() {
		c.setSaldo(10.92);
		c.rimborso(3.00);
		assertEquals(13.92,c.getSaldo(),0);
	}
	
	@Test
	public void testEquals() {
		CartaDiCredito c_1 = new CartaDiCredito("ab18k9292929j9991919l",78.99);
		CartaDiCredito c_2 = null;
		//stesso oggetto
		assertTrue(c.equals(c));
		//oggetti diversi con in comune il numeroDiCarta (valore univoco)
		assertTrue(c.equals(c_1));
		//diverso numero di carta
		c_1.setNumeroCarta("djkadjk");;
		assertFalse(c.equals(c_1));
		//oggetti diversi o nulli
		assertFalse(c.equals(11));
		assertFalse(c.equals(c_2));
	}
}
