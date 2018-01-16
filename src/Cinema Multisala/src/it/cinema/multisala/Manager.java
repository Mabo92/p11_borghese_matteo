package it.cinema.multisala;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe <code>Manager</code> contiene informazioni inerenti il
 * manager del circuito di cinema.
 * 
 * @version 1.00 10 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Manager extends Utente{

	/** Numero di dipendenti*/
	private int numeroDipendenti;
	
	/** Stipendio*/
	private double stipendio;
	
	/**
	 * Costruttore manager.
	 * 
	 * @param id id manage
	 * @param nome nome manager
	 * @param cognome cognome manager
	 * @param sesso sesso manager
	 * @param email email manager
	 * @param loggato stato
	 * @param password password manager
	 * @param passwordVerifica verifica immissione
	 * @param nomeUtente username
	 * @param fraseSegreta frase segreta
	 * @param numeroDipendenti nnumero dipendenti
	 * @param stipendio stipendio
	 */
	public Manager(int id, String nome, String cognome, String sesso, String email, boolean loggato, String password,
			String passwordVerifica, String nomeUtente, String fraseSegreta, int numeroDipendenti, double stipendio) {
		super(id, nome, cognome, sesso, email, loggato, password, passwordVerifica, nomeUtente, fraseSegreta);
		this.setNumeroDipendenti(numeroDipendenti);
		this.setStipendio(stipendio);
	}
	/**
	 * Get numero dipendenti.
	 * 
	 * @return numero dipendenti
	 */
	public int getNumeroDipendenti() {
		return numeroDipendenti;
	}

	/**
	 * Set numero dipendenti.
	 * 
	 * @param numeroDipendenti numero dipendenti
	 */
	public void setNumeroDipendenti(int numeroDipendenti) {
		this.numeroDipendenti = numeroDipendenti;
	}

	/**
	 * Get stipendio.
	 * 
	 * @return stipendio
	 */
	public double getStipendio() {
		return stipendio;
	}

	/**
	 * Set stipendio.
	 * 
	 * @param stipendio stipendio
	 */
	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}
	
	/**
	 * Permette di modificare i dati personali e ne verifica la correttezza. 
	 * 
	 * @param email email manager
	 * @param password password manager
	 * @param password1 verifica password manager
	 * @return true se modifica andata a buon fine, false altrimenti
	 */
	public boolean modificaDatiPersonali(String email, String password, String password1) {
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
		if(check){
			this.setEmail(email);
			this.setPassword(password);
			this.setPasswordVerifica(password1);
		}
		return check;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Manager))return false;
	    Manager m = (Manager)other;
	    //id e nome utente univoci
	    return this.getNomeUtente().equals(m.getNomeUtente())
	    		&& this.getId() == m.getId();
	}
}
