package it.cinema.multisala;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente extends Utente{
	
	/** Numero di telefono */
	private String numeroDiTelefono;
	
	/** Prenotazioni */
	private ArrayList<Prenotazione> prenotazioni;
	
	/** Carta di credito */
	private CartaDiCredito carta;
	
	/** Abbonamento */
	private Abbonamento abbonamento;

	/**
	 * Costruttore di cliente.
	 * 
	 * @param id id cliente
	 * @param nome nome cliente
	 * @param cognome cognome cliente
	 * @param sesso sesso cliente
	 * @param email email cliente
	 * @param loggato loggato cliente
	 * @param password password cliente
	 * @param passwordVerifica ridigita password
	 * @param nomeUtente username
	 * @param fraseSegreta frase segreta
	 * @param numeroDiTelefono numero di telefono
	 * @param carta carta di credito
	 * @param abbonamento abbonamento attivi
	 */
	public Cliente(int id, String nome, String cognome, String sesso, String email, boolean loggato, String password,
			String passwordVerifica, String nomeUtente, String fraseSegreta, String numeroDiTelefono, 
		    CartaDiCredito carta, Abbonamento abbonamento) {
		super(id, nome, cognome, sesso, email, loggato, password, passwordVerifica, nomeUtente, fraseSegreta);
		this.setNumeroDiTelefono(numeroDiTelefono);
		this.prenotazioni = new ArrayList<Prenotazione>();
		this.setCarta(carta);
		this.setAbbonamento(abbonamento);
	}

	/**
	 * Get numero di telefono.
	 * 
	 * @return numero di telefono
	 */
	public String getNumeroDiTelefono() {
		return numeroDiTelefono;
	}

	/**
	 * Set numero di telefono.
	 * 
	 * @param numeroDiTelefono numero di telefono
	 */
	public void setNumeroDiTelefono(String numeroDiTelefono) {
		this.numeroDiTelefono = numeroDiTelefono;
	}

	/**
	 * Get prenotazioni.
	 * 
	 * @return prenotazioni
	 */
	public ArrayList<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	/**
	 * Set prenotazioni.
	 * 
	 * @param prenotazioni prenotazione cliente
	 */
	public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	/**
	 * Get carta di credito.
	 * 
	 * @return carta di credito 
	 */
	public CartaDiCredito getCarta() {
		return carta;
	}

	/**
	 * Set carta di credito.
	 * 
	 * @param carta carta di credito
	 */
	public void setCarta(CartaDiCredito carta) {
		this.carta = carta;
	}
	
	/**
	 * Get abbonamento.
	 * 
	 * @return abbonamento
	 */
	public Abbonamento getAbbonamento() {
		return abbonamento;
	}

	/**
	 * Set abbonamento
	 * 
	 * @param abbonamento abbonamento da acquistare
	 */
	public void setAbbonamento(Abbonamento abbonamento) {
		this.abbonamento = abbonamento;
	}
	
	/**
	 * Permette di modificare i dati personali e ne verifica la correttezza. 
	 * 
	 * @param email email manager
	 * @param password password manager
	 * @param password1 verifica password manager
	 * @param carta numero carta di credito
	 * @return true se la modifica andata a buon fine, false altrimenti
	 */
	public boolean modificaDatiPersonali(String email, String password, String password1, String carta) {
		boolean check = true;
		String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]"
						+ "+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(!password.equals(password1)) {
			check = false;
			System.err.println("password non coincidente");
		}
		if (!matcher.matches()) {
			check = false;
			System.err.println("email non conforme");
		}
		if(carta.length() < 16){
			check = false;
			System.err.println("carta di credito non conforme");
		}
		if(check){
			this.setEmail(email);
			this.setPassword(password);
			this.setPasswordVerifica(password);
			this.carta.setNumeroCarta(carta);
		}
		return check;
	}

	
	/**
	 * Permette di prenotare dei biglietti.
	 * 
	 * @param p prenotazione
	 * @param metodoPagamento tipo di pagamento scelto
	 */
	public void prenotaBiglietti(Prenotazione p, String metodoPagamento) {
		double importo = p.calcolaTotale();
		if(metodoPagamento.equals("carta di credito")) {
			//scala l'importo dalla carta
			if(carta.pagamento(importo)){
				//prenota i posti in sala e aggiunge p a prenotazioni
				if(p.getProgrammazione().aggiungiPrenotazione(p)) {
					prenotazioni.add(p);
				}else{
					carta.rimborso(importo);
				}
			}
		}
		
		if(metodoPagamento.equals("abbonamento")) {
			if(abbonamento.aggiornaBiglietti(p.getNumeroDiBiglietti())){
				//scala biglietti dall'abbonamento
				if(p.getProgrammazione().aggiungiPrenotazione(p)) {
					prenotazioni.add(p);
				}else{
					abbonamento.rimborsaBiglietti(p.getNumeroDiBiglietti());
				}
			}
		}
	}
	
	/**
	 * Elimina una prenotazione esistente.
	 * 
	 * @param p prenotazione da eliminare
	 * @param metodoPagamento tipo di pagamento scelto
	 */
	public void CancellaPrenotazioneCliente(Prenotazione p, String metodoPagamento) {
		double importo = p.calcolaTotale();
		if(metodoPagamento.equals("carta di credito")) {
			if(p.getProgrammazione().cancellaPrenotazione(p.getIdPrenotazione())){
				carta.rimborso(importo);
			}
		}
		
		if(metodoPagamento.equals("abbonamento")) {
			if(p.getProgrammazione().cancellaPrenotazione(p.getIdPrenotazione())){
				abbonamento.rimborsaBiglietti(p.getNumeroDiBiglietti());
			}
		}
		
	}
	
	/**
	 * Restituisce tutte le programmazioni in quella data citta in quel dato giorno
	 * 
	 * @param cinema tutti i cenema appartenenti al circuito
	 * @param citta citta di cui si desidera ottenere la programmazione di tutti i film in tutti i cinema
	 * @param data della programmazione richiesta 
	 * @return lista delle programmazioni rispondenti ai criteri di ricerca
	 */
	public ArrayList<Programmazione> ricercaPerCitta(ArrayList<Cinema> cinema, 
													String citta, LocalDateTime data) {
		ArrayList<Programmazione> temp = new ArrayList<Programmazione>();
		for(Cinema c : cinema) {
			if(c.getCitta().equals(citta)) {
				for(Programmazione p : c.getProgrammazione()) {
					if(p.getGiorno().equals(data)) {
						temp.add(p);
					}
				}
			}
		}
		if(temp.isEmpty()){
			System.err.println("nessuna corrispondenza trovata");
		}
		return temp;
		
	}
	
	/**
	 * Restituisce tutte le programmazioni in quella data citta in quel dato cinema
	 * in quel dato giorno
	 * 
	 * @param cinemas tutti i cenema appartenenti al circuito
	 * @param citta citta di cui si desidera ricevere la programmazione di tutti i film
	 * @param cinema cinema di cui si desidera ricevere la programmazione di tutti i film
	 * @param data data della programmazione richiesta 
	 * @return lista delle programmazioni rispondenti ai criteri di ricerca
	 */
	public ArrayList<Programmazione> ricercaPerCinema(ArrayList<Cinema> cinemas,String citta,
													  String cinema, LocalDateTime data) {
		ArrayList<Programmazione> temp = new ArrayList<Programmazione>();
		for(Cinema c : cinemas) {
			if(c.getCitta().equals(citta) && c.getNome().equals(cinema)) {
				for(Programmazione p : c.getProgrammazione()) {
					if(p.getGiorno().equals(data)) {
						temp.add(p);
					}
				}
			}
		}
		if(temp.isEmpty()){
			System.err.println("nessuna corrispondenza trovata");
		}
		return temp;
	}
	
	/**
	 * Restituisce tutte le programmazioni in quella data citta in quel dato cinema
	 * in quel dato giorno di quel dato film
	 * 
	 * @param cinemas tutti i cenema appartenenti al circuito
	 * @param citta citta di cui si desidera ricevere la programmazione
	 * @param cinema cinema di cui si desidera ricevere la programmazione 
	 * @param titolo titolo del film di cui si desidera ricercare la programmazione
	 * @param data della programmazione richiesta 
	 * @return lista delle programmazioni rispondenti ai criteri di ricerca
	 */
	public ArrayList<Programmazione> ricercaPerFilm(ArrayList<Cinema> cinemas,String citta,
													String cinema, String titolo, LocalDateTime data) {
		ArrayList<Programmazione> temp = new ArrayList<Programmazione>();
		for(Cinema c : cinemas) {
			if(c.getCitta().equals(citta) && c.getNome().equals(cinema)) {
				for(Programmazione p : c.getProgrammazione()) {
					if(p.getGiorno().equals(data) && p.getFilm().getTitolo().equals(titolo)) {
						temp.add(p);
					}
				}
			}
		}
		if(temp.isEmpty()){
			System.err.println("nessuna corrispondenza trovata");
		}
		return temp;
	}
	
	/**
	 * Acquista un abbonamento 
	 * 
	 * @param a abbonamento che si desidera acquistare
	 */
	public void acquistaAbbonamento(Abbonamento a) {
		//non si può disporre di più di un abbonamento
		if(this.abbonamento != null)
		{
			System.err.println("Possiedi già un abbonamento");
		}
		if(this.abbonamento == null)
		{
			if(this.carta.pagamento(a.getCosto())) 
			{
				this.abbonamento = a;
			}else{
				System.err.println("Errore nel pagamento");
			}
		}		
	}
}
