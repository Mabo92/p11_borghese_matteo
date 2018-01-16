package it.cinema.multisala;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe <code>Circuito</code> contiene informazioni inerenti il 
 * circuito di cinema.
 * 
 * @version 1.00 05 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Circuito {
	
	/** Nome del circuito. */
	private String nome;
	
	/** Manager del circuito. */
	private Manager manager;
	
	/** Clienti del circuito. */
	private ArrayList<Cliente> clienti;
	
	/** Cinema apparteneti al circuito. */
	private ArrayList<Cinema> cinemaDelCircuito;
	
	/**
	 * Costruttore di circuito. 
	 * 
	 * @param nome nome del circuito
	 * @param manager manager del circuito
	 */
	public Circuito(String nome, Manager manager) {
		super();
		this.setNome(nome);;
		this.setManager(manager);;
		this.clienti = new ArrayList<Cliente>();
		this.cinemaDelCircuito = new ArrayList<Cinema>();
	}

	/**
	 * Get nome del circuito.
	 * 
	 * @return nome del circuito
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Set nome del circuito.
	 * 
	 * @param nome nome del circuito
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Get manager del circuito.
	 * 
	 * @return manager del circuito
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * Set manager del circuito.
	 * 
	 * @param manager manager del circuito
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	

	/**
	 * Get clienti del circuito.
	 * 
	 * @return clienti registrati al circuito
	 */
	public ArrayList<Cliente> getClienti() {
		return clienti;
	}

	/**
	 * Set clienti del circuito.
	 * 
	 * @param clienti clienti registrati al circuito
	 */
	public void setClienti(ArrayList<Cliente> clienti) {
		this.clienti = clienti;
	}

	/**
	 * Get lista cinema apparteneti al circuito.
	 * 
	 * @return tutti i cinema del circuito
	 */
	public ArrayList<Cinema> getCinemaDelCircuito() {
		return cinemaDelCircuito;
	}

	/**
	 * Set lista cinema apparteneti al circuito.
	 * 
	 * @param cinemaDelCircuito tutti i cinema del circuito
	 */
	public void setCinemaDelCircuito(ArrayList<Cinema> cinemaDelCircuito) {
		this.cinemaDelCircuito = cinemaDelCircuito;
	}
	
	/**
	 * Aggiunge un cinema al circuito.
	 * 
	 * @param c cinema da aggiungere
	 * @return true inserimento corretto, false altrimenti
	 */
	public boolean aggiungereCinema(Cinema c) {
		for(Cinema cine : cinemaDelCircuito){
			if(c.getNome() == null || c.getCitta() == null) {
				displayError();
				return false;
			}
			if(c.getIdCinema() == cine.getIdCinema()) {
				displayError();
				return false;
			}
		}
		cinemaDelCircuito.add(c);
		return true;
	}
	
	/**
	 * Elimina un film da quelli apparteneti al circuito 
	 * (sequece diagram UC20).
	 * 
	 * @param id id del cinema da eliminare
	 * @return true eliminazione corretto, false altrimenti
	 */
	public boolean eliminaCinema(int id) {
		int index = -1;
		for(Cinema c : cinemaDelCircuito) {
			if(c.getIdCinema() == id) {
				for(Programmazione p : c.getProgrammazione()){
					//verifico che non vi siano prenotazioni
					if(p.getPrenotazioni().size() == 0){
						index = cinemaDelCircuito.indexOf(c);
						cinemaDelCircuito.remove(index);
						return true;
					}
				}
			}
			
		}
		System.err.println("cinema non trovato o esistono delle prenotazioni attive"
				+ " impossibile rimuoverlo");
		return false;
	}
	
	/**
	 * Esegue il login.
	 * 
	 * @param nomeUtente nome dell'utente
	 * @param password possword utente
	 */
	public void login(String nomeUtente, String password) {
		if(manager.getPassword().equals(password) && manager.getNomeUtente().equals(nomeUtente)) {
			manager.setLoggato(true);
		}
		if(manager.getNomeUtente().equals(nomeUtente) && !manager.getPassword().equals(password)) {
			System.err.println("password errata. Frase segreta:" + manager.getFraseSegreta());
		}
		if(!manager.getNomeUtente().equals(nomeUtente) && manager.getPassword().equals(password)) {
			System.err.println("password errata");
		}
		if(!manager.getNomeUtente().equals(nomeUtente) && !manager.getPassword().equals(password)){
			System.err.println("campi inseriti errati");
		}
		
		for(Cliente c : this.clienti)
		 {
			 if(c.getNomeUtente().equals(nomeUtente) && c.getPassword().equals(password))
			 {
				 c.setLoggato(true);
			 }
			 if(c.getNomeUtente().equals(nomeUtente) && !c.getPassword().equals(password)){	 
				 System.err.println("Password errata. Frase segreta: "+c.getFraseSegreta());
			 }
			 if(!c.getNomeUtente().equals(nomeUtente) && !c.getPassword().equals(password)){
				 System.err.println("Dati inseriti non corretti");	
			 }
			 if(!c.getNomeUtente().equals(nomeUtente) && c.getPassword().equals(password)){
				 System.err.println("Username non corretto");	
			 }
		 } 
	}
	
	/**
	 * Esegue logout.
	 * 
	 * @param nomeUtente nome dell'utente
	 */
	public void logout(String nomeUtente) {
		if(manager.getNomeUtente().equals(nomeUtente)) {
			manager.setLoggato(false);
		}
		
		for(Cliente c : this.clienti)
		 {
			 if(c.getNomeUtente().equals(nomeUtente))
			 {
				 c.setLoggato(false);
			 }
		 }
	}
	
	/**
	 * Registra nuovo cliente.
	 * 
	 * @param c cliente da registrare
	 */
	public void registrazione(Cliente c) {
		boolean check = true;
		String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]"
						+ "+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(c.getEmail());
		for(Cliente cr : clienti) {
			if(cr.getNomeUtente().equals(c.getNomeUtente())) {
				check = false;
				System.err.println("username già esistente");
			}
		}
		if(!c.getPassword().equals(c.getPasswordVerifica())) {
			check = false;
			System.err.println("password non coincidente");
		}
		if (!matcher.matches()) {
			check = false;
			System.err.println("email non conforme");
		}
		if(c.getCarta().getNumeroCarta().length() < 16){
			check = false;
			System.err.println("carta di credito non conforme");
		}
		if(check == true){
			clienti.add(c);
		}
	}
	
	
	/**
	 * Visualizza un messaggio di errore.
	 */
	public void displayError() {
		System.err.println("Errore inserimento campi del cinema");
	}
	
}
