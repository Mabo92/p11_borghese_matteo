package it.test.strutturali;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import it.cinema.multisala.Abbonamento;
import it.cinema.multisala.CartaDiCredito;
import it.cinema.multisala.Cinema;
import it.cinema.multisala.Cliente;
import it.cinema.multisala.Film;
import it.cinema.multisala.Prenotazione;
import it.cinema.multisala.Programmazione;
import it.cinema.multisala.Recensione;
import it.cinema.multisala.Sala;

@RunWith(Parameterized.class)
public class ClienteTest {
	
	Cliente c;
	Abbonamento a;
	CartaDiCredito carta;
	Prenotazione p;
	int filaPosti[];
	int colonnaPosti[];
	ArrayList<Prenotazione> prenotazioni;
	Programmazione prog;
	Film f;
	Sala salaProg;
	LocalDateTime giorno;
	LocalTime oraInizio;
	LocalTime oraFine;
	
	@Parameter(0) public String email;
	@Parameter(1) public String password;
	@Parameter(2) public String passwordVerifica;
	@Parameter(3) public String numeroCarta;
	@Parameter(4) public boolean expectedValue;

	@Before
	public void setUp() throws Exception {
		filaPosti = new int[] {1, 1};
		colonnaPosti = new int[] {1, 2};		
		LocalTime durata;
		LocalDateTime dataUscita;
		LocalDateTime dataRimozione;
		Recensione r;
		Recensione r_1;
		ArrayList<Recensione> recensioni;
		durata = LocalTime.of(1 , 57);
		oraInizio = LocalTime.of(12, 30);
		oraFine = oraInizio.plusHours(durata.getHour());
		oraFine = oraFine.plusMinutes(oraInizio.getMinute());
		dataUscita = LocalDateTime.of(1982, Month.JUNE, 
									   25, 00, 00, 00);
		dataRimozione = LocalDateTime.of(1982, Month.SEPTEMBER,
										  25, 00, 00, 00);
		giorno = LocalDateTime.of(1992, Month.SEPTEMBER,
				  25, 00, 00, 00);
		f = new Film(1, "Blade Runner", "fatascienza", "Deckard è costretto...", 4.8, 
			5.50, durata, dataUscita, dataRimozione);
		r = new Recensione(1, 4, "bello...", "Luigi");
		r_1 = new Recensione(2, 2, "brutto...", "Mario");
		recensioni = new ArrayList<Recensione>();
		recensioni.add(r);
		recensioni.add(r_1);
		f.setRecensione(recensioni);
		salaProg = new Sala(1, "A", 2, 3);
		prog = new Programmazione(1, giorno, oraInizio, oraFine,
						 salaProg, f);
		p = new Prenotazione(1, 2, 5.50, prog);
		p.setColonnaPosti(colonnaPosti);
		p.setFilaPosti(filaPosti);
		prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(p);
		prog.setPrenotazioni(prenotazioni);
		a = new Abbonamento("standard", 10, 55.50);
		carta = new CartaDiCredito("ab18k9292929j9991919l",78.99);
		c = new Cliente(1, "Marco", "Rossi", "M", "a@g.it", true, "pass",
				"pass", "m.rossi", "frase", "555555", carta, a);
		c.setPrenotazioni(prenotazioni);
	}

	@Parameters
	public static Collection<Object[]> data(){ 
		return Arrays.asList(new Object[][] {
			{"email@g.com", "password", "password", "123456789101112130", true},
			{"email@g.com", "password", "confermaErrata", "123456789101112130", false}, 
			{"nonUnaEmail", "password123", "password123", "345", false},
			{"nonUnaEmail", "password13", "password123", "3456", false}
		});
	}


	@Test
	public void testCliente() {
		assertNotNull(c); 
		assertEquals(1,c.getId());
		assertEquals("Marco",c.getNome());
		assertEquals("Rossi",c.getCognome());
		assertEquals("M",c.getSesso());
		assertEquals("a@g.it",c.getEmail());
		assertTrue(c.isLoggato());
		assertEquals("pass",c.getPassword());
		assertEquals("pass",c.getPasswordVerifica());
		assertEquals("m.rossi", c.getNomeUtente());
		assertEquals("frase", c.getFraseSegreta());
		assertEquals("555555", c.getNumeroDiTelefono());
		assertEquals(carta, c.getCarta());
		assertEquals(a, c.getAbbonamento());
	}

	@Test
	public void testModificaDatiPersonali() {
		assertEquals(expectedValue, 
				c.modificaDatiPersonali(email, password, passwordVerifica, numeroCarta));
	}

	@Test
	public void testPrenotaBiglietti() {
		//falliscono entrambe numero biglietti in
		//abbonamento insufficienti, saldo carta insufficiente
		int fila[] = p.getFilaPosti();
		int colonna[] = p.getColonnaPosti();
		Prenotazione pren = new Prenotazione(1, 2, 50.50, prog);
		pren.setColonnaPosti(colonnaPosti);
		pren.setFilaPosti(filaPosti);
		prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(pren);
		prog.setPrenotazioni(prenotazioni);
		c.prenotaBiglietti(pren, "carta di credito");
		c.getAbbonamento().setnBigliettiRimanenti(1);
		c.prenotaBiglietti(pren, "abbonamento");
		//verifico che i posti siano rimasti liberi
		assertFalse(prog.getSalaProgrammazione().isPrenotato(fila[0], colonna[0]));
		assertFalse(prog.getSalaProgrammazione().isPrenotato(fila[1], colonna[1]));
		/* verifico che il saldo della carta e il numero dei biglietti
		 * rimanenti siano invariati */
		assertEquals(78.99, c.getCarta().getSaldo(), 0);
		assertEquals(1, c.getAbbonamento().getnBigliettiRimanenti());
		//prenotazione andata a buon fine carta di credito
		c.getAbbonamento().setnBigliettiRimanenti(10);
		c.prenotaBiglietti(p, "carta di credito");
		fila = p.getFilaPosti();
		colonna = p.getColonnaPosti();
		//verifico che i posti siano correttamente prenotati
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila[0], colonna[0]));
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila[1], colonna[1]));
		//verifico che sia stato scalato il pagamento dalla carta
		assertEquals(67.99, c.getCarta().getSaldo(), 0);
		assertTrue(c.getPrenotazioni().contains(p));
		//prenotazione fallita posti già prenotati
		c.prenotaBiglietti(p, "carta di credito");
		//resetto i prosti in sala
		salaProg = new Sala(1, "A", 2, 3);
		prog = new Programmazione(1, giorno, oraInizio, oraFine,
						 salaProg, f);
		Prenotazione pren_1 = new Prenotazione(1, 2, 5.50, prog);
		pren_1.setColonnaPosti(colonnaPosti);
		pren_1.setFilaPosti(filaPosti);
		prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni.add(pren_1);
		prog.setPrenotazioni(prenotazioni);
		//prenotazione andata a buon fine abbonamento
		c.prenotaBiglietti(pren_1, "abbonamento");
		int fila_1[] = p.getFilaPosti();
		int colonna_1[] = p.getColonnaPosti();
		//verifico che i posti siano correttamente prenotati
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila_1[0], colonna_1[0]));
		assertTrue(prog.getSalaProgrammazione().isPrenotato(fila_1[1], colonna_1[1]));
		//verifico che sia stato scalato il numero dei biglietti dall'abbonamento
		assertEquals(8, c.getAbbonamento().getnBigliettiRimanenti());
		assertTrue(c.getPrenotazioni().contains(pren_1));
		//prenotazione fallita posti già prenotati
		c.prenotaBiglietti(pren_1, "abbonamento");
		
	}

	@Test
	public void testCancellaPrenotazione() {
		/* superato orario utile per cancellare 
		* la prenotazione (proiezione film già avvenuta) */
		c.CancellaPrenotazioneCliente(p, "carta di credito");
		c.CancellaPrenotazioneCliente(p, "abbonamento");
		assertEquals(prog.getPrenotazioni().size(), c.getPrenotazioni().size());
		//cancello la prenotazione rimborso su carta
		int size = p.getProgrammazione().getPrenotazioni().size();
		int size_1 = c.getPrenotazioni().size();
		giorno = LocalDateTime.of(2019, Month.SEPTEMBER,
				25, 00, 00, 00);
		prog.setGiorno(giorno);
		p.setProgrammazione(prog);
		c.CancellaPrenotazioneCliente(p, "carta di credito");
		assertEquals(89.99, c.getCarta().getSaldo(), 0);
		assertEquals(size - 1, p.getProgrammazione().getPrenotazioni().size());
		assertEquals(size_1 - 1, c.getPrenotazioni().size());
		//cancello la prenotazione rimborso su carta
		c.getPrenotazioni().add(p);
		p.getProgrammazione().getPrenotazioni().add(p);
		size = p.getProgrammazione().getPrenotazioni().size();
		size_1 = c.getPrenotazioni().size();
		giorno = LocalDateTime.of(2019, Month.SEPTEMBER,
				  25, 00, 00, 00);
		prog.setGiorno(giorno);
		p.setProgrammazione(prog);
		c.CancellaPrenotazioneCliente(p, "abbonamento");
		assertEquals(12, c.getAbbonamento().getnBigliettiRimanenti());
		assertEquals(size - 1, p.getProgrammazione().getPrenotazioni().size());
		assertEquals(size_1 - 1, c.getPrenotazioni().size());
	}

	@Test
	public void testRicercaPerCitta() {
		Cinema cinema;
		LocalDateTime giorno_1 = LocalDateTime.of(1992, Month.SEPTEMBER,
				  27, 00, 00, 00);
		String citta_1 = "Torino";
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		cinema = new Cinema(1, "Fiumara", "Genova", "Via...");
		ArrayList<Programmazione> programmazioni = new ArrayList<Programmazione>();
		programmazioni.add(prog);
		cinema.setProgrammazione(programmazioni);
		ArrayList<Sala> sala = new ArrayList<Sala>();
		sala.add(salaProg);
		cinema.setSala(sala);
		cinemas.add(cinema);
		//non ci sono cinema in quella citta
		assertTrue(c.ricercaPerCitta(cinemas, citta_1, giorno).isEmpty());
		//non ci sono film in quel giorno
		assertTrue(c.ricercaPerCitta(cinemas, cinema.getCitta(), giorno_1).isEmpty());
		//ricerca corretta
		assertFalse(c.ricercaPerCitta(cinemas, cinema.getCitta(), giorno).isEmpty());
	}

	@Test
	public void testRicercaPerCinema() {
		Cinema cinema;
		LocalDateTime giorno_1 = LocalDateTime.of(1992, Month.SEPTEMBER,
				  27, 00, 00, 00);
		Cinema cinema_1 = new Cinema(2, "oden", "Torino", "Via...");
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		cinema = new Cinema(1, "Fiumara", "Genova", "Via...");
		ArrayList<Programmazione> programmazioni = new ArrayList<Programmazione>();
		programmazioni.add(prog);
		cinema.setProgrammazione(programmazioni);
		ArrayList<Sala> sala = new ArrayList<Sala>();
		sala.add(salaProg);
		cinema.setSala(sala);
		cinemas.add(cinema);
		//nella citta scelta non esiste il cinema indicato
		assertTrue(c.ricercaPerCinema(cinemas, cinema.getCitta(), 
				cinema_1.getNome(), giorno).isEmpty());
		assertTrue(c.ricercaPerCinema(cinemas, cinema_1.getCitta(), 
                cinema.getNome(), giorno).isEmpty());
		//nessun film in programmazione nel cinema e nel girono richiesto
		assertTrue(c.ricercaPerCinema(cinemas, cinema.getCitta(), 
				cinema.getNome(), giorno_1).isEmpty());
		//ricerca corretta
		assertFalse(c.ricercaPerCinema(cinemas, cinema.getCitta(), 
				cinema.getNome(), giorno).isEmpty());
	}

	@Test
	public void testRicercaPerFilm() {
		Cinema cinema;
		LocalDateTime giorno_1 = LocalDateTime.of(1992, Month.SEPTEMBER,
				  27, 00, 00, 00);
		Cinema cinema_1 = new Cinema(2, "oden", "Torino", "Via...");
		String titoloFilm = "Il padrino";
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		cinema = new Cinema(1, "Fiumara", "Genova", "Via...");
		ArrayList<Programmazione> programmazioni = new ArrayList<Programmazione>();
		programmazioni.add(prog);
		cinema.setProgrammazione(programmazioni);
		ArrayList<Sala> sala = new ArrayList<Sala>();
		sala.add(salaProg);
		cinema.setSala(sala);
		cinemas.add(cinema);
		//nella citta scelta non esiste il cinema indicato
		assertTrue(c.ricercaPerFilm(cinemas, cinema.getCitta(), 
				cinema_1.getNome(),f.getTitolo(), giorno).isEmpty());
		assertTrue(c.ricercaPerFilm(cinemas, cinema_1.getCitta(), 
				cinema.getNome(),f.getTitolo(), giorno).isEmpty());
		//il film non e' in programmazione nel cinema e nel girono richiesto
		assertTrue(c.ricercaPerFilm(cinemas, cinema.getCitta(), 
				cinema.getNome(), f.getTitolo(), giorno_1).isEmpty());
		assertTrue(c.ricercaPerFilm(cinemas, cinema.getCitta(), 
				cinema.getNome(), titoloFilm, giorno).isEmpty());
		//ricerca corretta
		assertFalse(c.ricercaPerFilm(cinemas, cinema.getCitta(), 
				cinema.getNome(), f.getTitolo(), giorno).isEmpty());
	}

	@Test
	public void testAcquistaAbbonamento() {
		double saldoCarta = c.getCarta().getSaldo();
		double costoAbbonamento = c.getAbbonamento().getCosto();
		double saldoAggiornato = saldoCarta - costoAbbonamento;
		//il cliente dispone gia di un abbonamento
		c.acquistaAbbonamento(a);
		assertNotNull(c.getAbbonamento());
		assertEquals(saldoCarta, 
				c.getCarta().getSaldo(), 0);
		//acquisto abbonamento
		c.setAbbonamento(null);
		c.acquistaAbbonamento(a);
		assertEquals(saldoAggiornato, 
				c.getCarta().getSaldo(), 0);
		//non dispongo di denaro sufficiente per acquistare
		c.setAbbonamento(null);
		c.getCarta().setSaldo(10.00);
		c.acquistaAbbonamento(a);
		assertEquals(c.getCarta().getSaldo(), 
				c.getCarta().getSaldo(), 0);
		assertNull(c.getAbbonamento());
	}

}
