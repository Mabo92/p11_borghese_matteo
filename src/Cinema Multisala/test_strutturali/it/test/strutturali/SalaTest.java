package it.test.strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.cinema.multisala.Sala;

public class SalaTest {
	
	Sala s;

	@Before
	public void setUp() throws Exception {
		s = new Sala(1, "A", 2, 3);
	}

	@Test
	public void testSala() {
		assertNotNull(s);
		assertEquals(1,s.getIdSala());
		assertEquals("A",s.getNomeSala());
		assertEquals(2,s.getNumeroFile());
		assertEquals(3,s.getPostiPerFila());
		assertEquals(6,s.getPostiTotali());
		assertEquals(6,s.getPostiLiberi());
		assertNotNull(s.getPosti());
		assertEquals(2,s.getPosti().length);
	}

	@Test
	public void testGetIdSala() {
		s.setIdSala(2);
		assertEquals(2,s.getIdSala());
	}

	@Test
	public void testGetNomeSala() {
		s.setNomeSala("B");
		assertEquals("B",s.getNomeSala());
	}

	@Test
	public void testGetNumeroFile() {
		s.setNumeroFile(6);
		assertEquals(6,s.getNumeroFile());
	}

	@Test
	public void testGetPostiPerFila() {
		s.setPostiPerFila(3);
		assertEquals(3,s.getPostiPerFila());
	}

	@Test
	public void testGetPosti() {
		boolean posti[][] = new boolean[3][3];
		s.setPosti(posti);
		assertArrayEquals(posti,s.getPosti());
	}
	
	@Test
	public void testGetPostiTotali() {
		s.setPostiTotali(45);
		assertEquals(45,s.getPostiTotali());
	}

	@Test
	public void testGetPostiLiberi() {
		s.setPostiLiberi(3);
		assertEquals(3,s.getPostiLiberi());
	}

	@Test
	public void testIsPrenotato() {
		boolean[][] posti = new boolean[][] {
	        new boolean[] {true, false, false},
	        new boolean[] {false, true, true},
	    };
	    s.setPosti(posti);
	    assertTrue(s.isPrenotato(0, 0));
	    assertFalse(s.isPrenotato(1, 0));
	}

	@Test
	public void testPrenotaPosti() {
		boolean[][] posti = new boolean[][] {
	        new boolean[] {true, false, false},
	        new boolean[] {false, true, true},
	    };
	    int[] file = {0, 0};
	    int[] colonne = {0, 1};
	    int[] file_1 = {0, 0, 1, 1};
	    int[] colonne_1 = {1, 2, 0, 1};
	    int[] file_2 = {0, 0};
	    int[] colonne_2 = {1, 2};
	    boolean expectedPosto[][] = null;
	    s.setPosti(posti);
	    s.setPostiLiberi(3);
	    
	    //dimensione file colonne diversa, errore formattazione dati
	    assertFalse(s.prenotaPosti(file, colonne_1));
	    //numero posti liberi insufficiente
	    assertFalse(s.prenotaPosti(file_1, colonne_1));
	    //posto {0,0} già prenotato
	    assertFalse(s.prenotaPosti(file, colonne));
	    //eseguo la prenotazione di due posti
	    assertTrue(s.prenotaPosti(file_2, colonne_2));
	    expectedPosto = s.getPosti();
	    assertTrue(expectedPosto[0][1]);
	    assertTrue(expectedPosto[0][2]);
	    assertEquals(1,s.getPostiLiberi());
	}
	
	@Test
	public void testLiberaPosti() {
		boolean[][] posti = new boolean[][] {
	        new boolean[] {true, false, false},
	        new boolean[] {false, true, true},
	    };
	    int[] file = {1, 1};
	    int[] colonne = {1, 2};
	    int[] file_1 = {0, 0, 1, 1};
	    boolean expectedPosto[][] = null;
	    s.setPosti(posti);
	    s.setPostiLiberi(3);
	    
	    //dimensione file colonne diversa, errore formattazione dati
	    assertFalse(s.liberaPosti(file_1, colonne));
	    //libero due posti
	    assertTrue(s.liberaPosti(file, colonne));
	    expectedPosto = s.getPosti();
	    assertFalse(expectedPosto[1][1]);
	    assertFalse(expectedPosto[1][2]);
	    assertEquals(5,s.getPostiLiberi());
	}
	
}
