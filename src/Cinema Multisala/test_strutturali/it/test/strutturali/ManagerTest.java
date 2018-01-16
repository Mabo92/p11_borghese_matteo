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

import it.cinema.multisala.Manager;

@RunWith(Parameterized.class)
public class ManagerTest {
	
	Manager m;
	@Parameter(0) public String email;
	@Parameter(1) public String password;
	@Parameter(2) public String passwordVerifica;
	@Parameter(3) public boolean expectedValue;

	@Before
	public void setUp() throws Exception {
		m = new Manager(1, "Kevin", "bianchi", "M", "email@g.com", true, "pass",
			"pass", "K.bianchi", "frase", 50, 2048.99);
	}
	
	@Parameters
	public static Collection<Object[]> data(){ 
		return Arrays.asList(new Object[][] {
			{"email@g.com", "password", "password", true},
			{"email@g.com", "password", "confermaErrata", false}, 
			{"nonUnaEmail", "password123", "password123", false},
			{"nonUnaEmail", "password13", "password123", false}
		});
	}

	@Test
	public void testManager() {
		assertNotNull(m); 
		assertEquals(1,m.getId());
		assertEquals("Kevin",m.getNome());
		assertEquals("bianchi",m.getCognome());
		assertEquals("M",m.getSesso());
		assertEquals("email@g.com",m.getEmail());
		assertTrue(m.isLoggato());
		assertEquals("pass",m.getPassword());
		assertEquals("pass",m.getPasswordVerifica());
		assertEquals("K.bianchi", m.getNomeUtente());
		assertEquals("frase", m.getFraseSegreta());
		assertEquals(50, m.getNumeroDipendenti());
		assertEquals(2048.99, m.getStipendio(), 0);
	}

	@Test
	public void testModificaDatiPersonali() {
		/* verifico tutti i casi possibili: tutto corretto,
		 * email corretta password errata e viceversa, tutto
		 * sbagliato
		 */
		assertEquals(expectedValue, 
				m.modificaDatiPersonali(email, password, passwordVerifica));
	}
	
	@Test
	public void testEquals() {
		Manager m_1 = new Manager(1, "Kevin", "bianchi", "M", "email@g.com", true, "pass",
				"pass", "K.bianchi", "frase", 50, 2048.99);
		Manager m_2 = null;
		//stesso oggetto
		assertTrue(m.equals(m));
		//oggetti diversi con in comune id, username (valori univoci).
		assertTrue(m.equals(m_1));
		//diverso id
		m_1.setId(2);
		assertFalse(m.equals(m_1));
		//diverso nome utente
		m_1.setId(1);
		m_1.setNomeUtente("sbagliato");
		assertFalse(m.equals(m_1));
		//oggetti diversi o nulli
		assertFalse(m.equals(11));
		assertFalse(m.equals(m_2));
	}

}
